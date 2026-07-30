package com.airesumeanalyzer.ats.client;

import com.airesumeanalyzer.ats.dto.ResumeResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResumeServiceClient {

    private final RestTemplate restTemplate;

    public ResumeServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResumeResponse getResume(Long resumeId) {

        String url = "http://localhost:8082/api/resumes/" + resumeId;

        return restTemplate.getForObject(url, ResumeResponse.class);
    }
}