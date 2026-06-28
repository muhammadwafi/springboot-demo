package com.spring.demo.app.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static void refreshAuthentication(CustomUserDetails userDetails) {
        Authentication current = SecurityContextHolder.getContext().getAuthentication();

        Authentication updated = new UsernamePasswordAuthenticationToken(
            userDetails,
            current.getCredentials(),
            userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(updated);

    }
}
