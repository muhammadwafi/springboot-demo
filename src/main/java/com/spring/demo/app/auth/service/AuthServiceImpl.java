package com.spring.demo.app.auth.service;

import com.spring.demo.app.auth.dto.ChangePasswordRequest;
import com.spring.demo.app.auth.dto.RegisterRequest;
import com.spring.demo.app.auth.exception.CurrentPasswordIncorrectException;
import com.spring.demo.app.auth.exception.EmailAlreadyExistsException;
import com.spring.demo.app.auth.exception.UserNotFoundException;
import com.spring.demo.app.auth.exception.UsernameAlreadyExistsException;
import com.spring.demo.app.auth.mapper.AuthMapper;
import com.spring.demo.app.user.entity.User;
import com.spring.demo.app.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsUserByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user = authMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // set user active
        user.setIsActive(true);

        userRepository.save(user);
    }

    @Override
    public void changePassword(UUID userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new CurrentPasswordIncorrectException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}
