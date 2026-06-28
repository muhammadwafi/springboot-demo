package com.spring.demo.app.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CurrentUserResponse {

    private UUID id;
    private String username;
    private String email;
    private String fullName;

}
