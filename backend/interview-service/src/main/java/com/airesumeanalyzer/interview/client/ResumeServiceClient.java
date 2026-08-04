package com.airesumeanalyzer.interview.client;

import com.airesumeanalyzer.interview.dto.ResumeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "resume-service")
public interface ResumeServiceClient {

    @GetMapping("/api/resumes/{id}")
    ResumeResponse getResume(@PathVariable Long id);

}