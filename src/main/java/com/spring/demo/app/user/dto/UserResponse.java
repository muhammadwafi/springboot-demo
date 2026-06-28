package com.spring.demo.app.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {

    private UUID id;
    private String username;
    private String email;
    private String fullName;
    private Boolean enabled;

}
