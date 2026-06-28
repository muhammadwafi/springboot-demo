package com.spring.demo.app.user.service;

import com.spring.demo.app.user.dto.UserCreateRequest;
import com.spring.demo.app.user.dto.UserResponse;
import com.spring.demo.app.user.dto.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * Get all users
     *
     * @return List or users
     */
    List<UserResponse> findAll();

    /**
     * Find a user by id
     *
     * @param id User ID.
     * @return User
     */
    UserResponse findById(UUID id);

    /**
     * Create a new user.
     *
     * @param request User creation request.
     */
    void create(UserCreateRequest request);

    /**
     * Update an existing user.
     *
     * @param id      User ID.
     * @param request User update request.
     */
    void update(UUID id, UserUpdateRequest request);

    /**
     * Delete a user.
     *
     * @param id User ID.
     */
    void delete(UUID id);

}
