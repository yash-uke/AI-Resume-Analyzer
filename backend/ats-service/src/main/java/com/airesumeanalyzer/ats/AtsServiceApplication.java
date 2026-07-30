package com.airesumeanalyzer.ats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AtsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtsServiceApplication.class, args);
    }
}