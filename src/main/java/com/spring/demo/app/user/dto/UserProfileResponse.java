package com.spring.demo.app.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserProfileResponse {

    private UUID id;
    private String fullName;
    private String email;
    private String username;
    private String isActive;

}
