package com.airesumeanalyzer.auth.controller;

import com.airesumeanalyzer.auth.dto.SendOtpRequest;
import com.airesumeanalyzer.auth.dto.VerifyOtpRequest;
import com.airesumeanalyzer.auth.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send-otp")
    public String sendOtp(@Valid @RequestBody SendOtpRequest request) {

        otpService.sendOtp(request.getEmail());

        return "OTP sent successfully.";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {

        boolean valid = otpService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        if (valid) {
            return "OTP verified successfully.";
        }

        return "Invalid or expired OTP.";
    }
}