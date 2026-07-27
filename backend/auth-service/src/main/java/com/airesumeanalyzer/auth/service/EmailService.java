package com.airesumeanalyzer.auth.service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

}