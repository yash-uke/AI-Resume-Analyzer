package com.airesumeanalyzer.auth.controller;

import com.airesumeanalyzer.auth.dto.AuthResponse;
import com.airesumeanalyzer.auth.dto.RegisterRequest;
import com.airesumeanalyzer.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}