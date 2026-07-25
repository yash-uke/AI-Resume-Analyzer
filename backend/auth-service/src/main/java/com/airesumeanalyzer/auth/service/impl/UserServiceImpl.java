package com.airesumeanalyzer.auth.service.impl;

import com.airesumeanalyzer.auth.dto.AuthResponse;
import com.airesumeanalyzer.auth.dto.RegisterRequest;
import com.airesumeanalyzer.auth.entity.User;
import com.airesumeanalyzer.auth.repository.UserRepository;
import com.airesumeanalyzer.auth.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role("USER")
                .build();

        userRepository.save(user);

        return new AuthResponse("User Registered Successfully");
    }
}