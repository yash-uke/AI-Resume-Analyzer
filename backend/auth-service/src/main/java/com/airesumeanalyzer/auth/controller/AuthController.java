package com.airesumeanalyzer.auth.controller;

import com.airesumeanalyzer.auth.dto.*;
import com.airesumeanalyzer.auth.service.OtpService;
import com.airesumeanalyzer.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final OtpService otpService;

    public AuthController(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {

        otpService.sendForgotPasswordOtp(request.getEmail());

        return ResponseEntity.ok("Password reset OTP sent successfully.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {

        userService.resetPassword(
                request.getEmail(),
                request.getOtp(),
                request.getNewPassword()
        );

        return ResponseEntity.ok("Password reset successfully.");
    }
}