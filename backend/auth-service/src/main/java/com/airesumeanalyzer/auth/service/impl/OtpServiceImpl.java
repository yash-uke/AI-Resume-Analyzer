package com.airesumeanalyzer.auth.service.impl;

import com.airesumeanalyzer.auth.entity.Otp;
import com.airesumeanalyzer.auth.repository.OtpRepository;
import com.airesumeanalyzer.auth.service.EmailService;
import com.airesumeanalyzer.auth.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final EmailService emailService;

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
    public boolean verifyOtp(String email, String otp) {

        Otp savedOtp = otpRepository
                .findByEmailAndOtp(email, otp)
                .orElse(null);

        if (savedOtp == null) {
            return false;
        }

        if (savedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        return true;
    }
}