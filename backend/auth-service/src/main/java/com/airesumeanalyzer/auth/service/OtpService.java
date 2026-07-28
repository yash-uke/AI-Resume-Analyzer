package com.airesumeanalyzer.auth.service;

public interface OtpService {

    void sendOtp(String email);

    void sendForgotPasswordOtp(String email);

    boolean verifyRegistrationOtp(String email, String otp);

    boolean verifyResetPasswordOtp(String email, String otp);
}