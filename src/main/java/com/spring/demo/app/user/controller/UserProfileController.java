package com.spring.demo.app.user.controller;

import com.spring.demo.app.auth.exception.EmailAlreadyExistsException;
import com.spring.demo.app.auth.exception.UsernameAlreadyExistsException;
import com.spring.demo.app.common.security.CustomUserDetails;
import com.spring.demo.app.user.dto.UserUpdateRequest;
import com.spring.demo.app.user.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService profileService;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetails currentUser, Model model) {
        model.addAttribute("form", profileService.getProfile(currentUser.getId()));

        return "user/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
        @AuthenticationPrincipal CustomUserDetails currentUser,
        @Valid @ModelAttribute("form") UserUpdateRequest request,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return "user/profile";
        }

        try {
            profileService.updateProfile(currentUser.getId(), request);
        } catch (UsernameAlreadyExistsException e) {
            result.rejectValue("username", "username.exists", e.getMessage());
        } catch (EmailAlreadyExistsException e) {
            result.rejectValue("email", "email.exists", e.getMessage());
        }

        if (result.hasErrors()) {
            return "user/profile";
        }

        return "redirect:/profile";
    }

}
