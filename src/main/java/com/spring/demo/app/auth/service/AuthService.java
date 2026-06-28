package com.spring.demo.app.auth.service;

import com.spring.demo.app.auth.dto.ChangePasswordRequest;
import com.spring.demo.app.auth.dto.RegisterRequest;

import java.util.UUID;

public interface AuthService {

    /**
     * Register new user.
     *
     * @param request
     */
    void register(RegisterRequest request);

    /**
     * Change password for existing user
     *
     * @param userId User identifier.
     * @param request Password change request.
     */
    void changePassword(UUID userId, ChangePasswordRequest request);

}
