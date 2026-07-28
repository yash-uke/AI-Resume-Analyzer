package com.airesumeanalyzer.auth.service.impl;

import com.airesumeanalyzer.auth.dto.AuthResponse;
import com.airesumeanalyzer.auth.dto.LoginRequest;
import com.airesumeanalyzer.auth.dto.RegisterRequest;
import com.airesumeanalyzer.auth.entity.User;
import com.airesumeanalyzer.auth.jwt.JwtService;
import com.airesumeanalyzer.auth.repository.UserRepository;
import com.airesumeanalyzer.auth.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.airesumeanalyzer.auth.exception.EmailAlreadyExistsException;
import com.airesumeanalyzer.auth.exception.InvalidCredentialsException;
import com.airesumeanalyzer.auth.service.OtpService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final OtpService otpService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, OtpService otpService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.otpService = otpService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .verified(false)          // New field
                .build();

        userRepository.save(user);

// Send OTP after registration
        otpService.sendOtp(user.getEmail());

        return new AuthResponse(
                "User Registered Successfully. Please verify your email.",
                null
        );
    }
    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

// Check email verification
        if (!user.isVerified()) {
            throw new RuntimeException("Please verify your email first.");
        }

// Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Email or Password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                "Login Successful",
                token
        );
    }
    @Override
    @Transactional
    public void resetPassword(String email, String otp, String newPassword) {

        boolean verified = otpService.verifyResetPasswordOtp(email, otp);

        if (!verified) {
            throw new RuntimeException("Invalid or expired OTP.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}