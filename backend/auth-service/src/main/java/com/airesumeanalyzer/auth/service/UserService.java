package com.airesumeanalyzer.auth.service;

import com.airesumeanalyzer.auth.dto.AuthResponse;
import com.airesumeanalyzer.auth.dto.LoginRequest;
import com.airesumeanalyzer.auth.dto.RegisterRequest;

public interface UserService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    void resetPassword(String email, String otp, String newPassword);
}