package com.airesumeanalyzer.auth.repository;

import com.airesumeanalyzer.auth.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByEmail(String email);

    Optional<Otp> findByEmailAndOtp(String email, String otp);

    @Transactional
    @Modifying
    void deleteByEmail(String email);
}