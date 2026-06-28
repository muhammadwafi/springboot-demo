package com.spring.demo.app.auth.controller;

import com.spring.demo.app.auth.dto.LoginRequest;
import com.spring.demo.app.auth.dto.RegisterRequest;
import com.spring.demo.app.auth.exception.EmailAlreadyExistsException;
import com.spring.demo.app.auth.exception.UsernameAlreadyExistsException;
import com.spring.demo.app.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new LoginRequest());

        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("form", new RegisterRequest());

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("form") RegisterRequest request,
            BindingResult result
    ) {

        try {
            authService.register(request);
        } catch (UsernameAlreadyExistsException e) {
            result.rejectValue(
                    "username",
                    "username.exists",
                    "Username is already taken."
            );
        } catch (EmailAlreadyExistsException e) {
            result.rejectValue(
                    "email",
                    "email.exists",
                    "Email is already registered."
            );
        }

        if (result.hasErrors()) {
            return "auth/register";
        }

        return "redirect:/auth/login";
        //        if (result.hasErrors()) {
//            return "auth/register";
//        }
//
//        authService.register(request);
//
//        return "redirect:/auth/login";
    }

}
