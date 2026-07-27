package com.airesumeanalyzer.auth.controller;

import com.airesumeanalyzer.auth.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/api/email/test")
    public String sendTestMail() {

        emailService.sendEmail(
                "yashuke2003@gmail.com",
                "AI Resume Analyzer",
                "Congratulations! Your Spring Boot Email Service is working successfully."
        );

        return "Email Sent Successfully";
    }
}