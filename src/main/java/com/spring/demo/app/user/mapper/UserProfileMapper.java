package com.spring.demo.app.user.mapper;

import com.spring.demo.app.user.dto.UserUpdateRequest;
import com.spring.demo.app.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    /**
     * Maps a user entity to the profile form.
     *
     * @param user User entity.
     * @return Profile form.
     */
    UserUpdateRequest toRequest(User user);

    /**
     * Updates the profile fields of an existing user.
     * <p>
     * Password, timestamps, and identifier are not modified.
     *
     * @param request Profile update request.
     * @param user    Existing user entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateEntity(UserUpdateRequest request, @MappingTarget User user);

}