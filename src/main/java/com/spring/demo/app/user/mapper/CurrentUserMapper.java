package com.spring.demo.app.user.mapper;

import com.spring.demo.app.user.dto.CurrentUserResponse;
import com.spring.demo.app.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrentUserMapper {

    /**
     * Current user response
     *
     * @param user
     * @return User entity
     */
    CurrentUserResponse toResponse(User user);

}
