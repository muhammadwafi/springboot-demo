package com.spring.demo.app.common.web;

import com.spring.demo.app.common.security.CustomUserDetails;
import com.spring.demo.app.user.dto.CurrentUserResponse;
import com.spring.demo.app.user.mapper.CurrentUserMapper;
import com.spring.demo.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final UserRepository repository;
    private final CurrentUserMapper mapper;

    @ModelAttribute("currentUser")
    public CurrentUserResponse currentUser(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        if (userDetails == null) {
            return null;
        }

        return repository.findById(userDetails.getId())
            .map(mapper::toResponse)
            .orElse(null);
    }

}