package com.spring.demo.app.user.service;

import com.spring.demo.app.auth.exception.EmailAlreadyExistsException;
import com.spring.demo.app.auth.exception.UserNotFoundException;
import com.spring.demo.app.auth.exception.UsernameAlreadyExistsException;
import com.spring.demo.app.user.dto.UserCreateRequest;
import com.spring.demo.app.user.dto.UserResponse;
import com.spring.demo.app.user.dto.UserUpdateRequest;
import com.spring.demo.app.user.entity.User;
import com.spring.demo.app.user.mapper.UserMapper;
import com.spring.demo.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    @Override
    public UserResponse findById(UUID id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());

        return mapper.toResponse(user);
    }

    @Override
    public void create(UserCreateRequest request) {
        validateUnique(request.getUsername(), request.getEmail());

        User user = mapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setIsActive(true);

        repository.save(user);
    }

    @Override
    public void update(UUID id, UserUpdateRequest request) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());

        validateUnique(request.getUsername(), request.getEmail());

        mapper.updateEntity(request, user);

        repository.save(user);
    }

    @Override
    public void delete(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException());

        repository.delete(user);
    }

    /**
     * Validates username and email
     *
     * @param username
     * @param email
     * @throws UsernameAlreadyExistsException if username already exist
     * @throws EmailAlreadyExistsException    if email already exist
     */
    private void validateUnique(String username, String email) {
        if (repository.existsUserByUsername(username)) {
            throw new UsernameAlreadyExistsException();
        }

        if (repository.existsUserByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

}
