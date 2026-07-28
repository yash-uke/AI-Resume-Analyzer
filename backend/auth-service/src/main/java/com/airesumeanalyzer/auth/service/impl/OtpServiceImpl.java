package com.airesumeanalyzer.auth.service.impl;

import com.airesumeanalyzer.auth.entity.Otp;
import com.airesumeanalyzer.auth.entity.User;
import com.airesumeanalyzer.auth.repository.OtpRepository;
import com.airesumeanalyzer.auth.repository.UserRepository;
import com.airesumeanalyzer.auth.service.EmailService;
import com.airesumeanalyzer.auth.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public void sendOtp(String email) {

        // Delete old OTP
        otpRepository.deleteByEmail(email);

        // Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // Create OTP entity
        Otp otpEntity = Otp.builder()
                .email(email)
                .otp(otp)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .build();

        // Save OTP
        otpRepository.save(otpEntity);

        // Send Email
        emailService.sendEmail(
                email,
                "AI Resume Analyzer - OTP Verification",
                "Your OTP is: " + otp + "\n\nThis OTP is valid for 5 minutes."
        );
    }

    @Override
    public void sendForgotPasswordOtp(String email) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        sendOtp(email);
    }

    private Otp validateOtp(String email, String otp) {

        Otp savedOtp = otpRepository
                .findByEmailAndOtp(email, otp)
                .orElse(null);

        if (savedOtp == null) {
            return null;
        }

        if (savedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            return null;
        }

        return savedOtp;
    }

    @Override
    @Transactional
    public boolean verifyRegistrationOtp(String email, String otp) {

        Otp savedOtp = validateOtp(email, otp);

        if (savedOtp == null) {
            return false;
        }

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return false;
        }

        user.setVerified(true);
        userRepository.save(user);

        otpRepository.deleteByEmail(email);

        return true;
    }

    @Override
    @Transactional
    public boolean verifyResetPasswordOtp(String email, String otp) {

        Otp savedOtp = validateOtp(email, otp);

        if (savedOtp == null) {
            return false;
        }

        otpRepository.deleteByEmail(email);

        return true;
    }
}