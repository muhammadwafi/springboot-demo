package com.spring.demo.app.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "Username should not be blank")
    private String username;

    @NotBlank(message = "Full name should not be blank")
    private String fullName;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email provided")
    private String email;

    private Boolean isActive;

}
