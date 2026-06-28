package com.spring.demo.app.user.service;

import com.spring.demo.app.user.dto.UserUpdateRequest;

import java.util.UUID;

public interface UserProfileService {

    /**
     * Gets the profile of the currently authenticated user.
     *
     * @param id User ID.
     * @return Profile information.
     */
    UserUpdateRequest getProfile(UUID id);

    /**
     * Updates the profile of the currently authenticated user.
     *
     * @param id      User ID.
     * @param request Profile update request.
     */
    void updateProfile(UUID id, UserUpdateRequest request);

}