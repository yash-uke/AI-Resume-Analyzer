package com.airesumeanalyzer.auth.service.impl;

import com.airesumeanalyzer.auth.dto.AuthResponse;
import com.airesumeanalyzer.auth.dto.LoginRequest;
import com.airesumeanalyzer.auth.dto.RegisterRequest;
import com.airesumeanalyzer.auth.entity.User;
import com.airesumeanalyzer.auth.repository.UserRepository;
import com.airesumeanalyzer.auth.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.airesumeanalyzer.auth.exception.EmailAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                .role("USER")
                .build();

        userRepository.save(user);

        return new AuthResponse(
                "User Registered Successfully",
                null
        );
    }
    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Email or Password");
        }

        return new AuthResponse(
                "Login Successful",
                null
        );
    }
}