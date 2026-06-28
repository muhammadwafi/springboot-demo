package com.spring.demo.app.user.service;

import com.spring.demo.app.auth.exception.EmailAlreadyExistsException;
import com.spring.demo.app.auth.exception.UserNotFoundException;
import com.spring.demo.app.auth.exception.UsernameAlreadyExistsException;
import com.spring.demo.app.user.dto.UserUpdateRequest;
import com.spring.demo.app.user.entity.User;
import com.spring.demo.app.user.mapper.UserProfileMapper;
import com.spring.demo.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository repository;
    private final UserProfileMapper mapper;

    @Override
    public UserUpdateRequest getProfile(UUID id) {

        User user = repository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        return mapper.toRequest(user);
    }

    @Override
    public void updateProfile(UUID id, UserUpdateRequest request) {

        User user = repository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        if (!user.getUsername().equals(request.getUsername())
            && repository.existsUserByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (!user.getEmail().equals(request.getEmail())
            && repository.existsUserByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        mapper.updateEntity(request, user);

        repository.save(user);
    }

}