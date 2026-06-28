package com.spring.demo.app.user.mapper;

import com.spring.demo.app.user.dto.UserCreateRequest;
import com.spring.demo.app.user.dto.UserResponse;
import com.spring.demo.app.user.dto.UserUpdateRequest;
import com.spring.demo.app.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Maps a User entity to a response DTO.
     *
     * @param user User entity.
     * @return User response.
     */
    UserResponse toResponse(User user);

    /**
     * Maps a create request to a new User entity.
     *
     * @param request User creation request.
     * @return User entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User toEntity(UserCreateRequest request);

    /**
     * Updates an existing user entity.
     *
     * @param request Update request.
     * @param user    Existing entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntity(UserUpdateRequest request, @MappingTarget User user);

}
