package com.spring.demo.app.auth.mapper;

import com.spring.demo.app.auth.dto.RegisterRequest;
import com.spring.demo.app.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User toEntity(RegisterRequest request);

}
