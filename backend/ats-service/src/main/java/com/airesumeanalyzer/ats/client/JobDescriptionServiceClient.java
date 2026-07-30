package com.airesumeanalyzer.ats.client;

import com.airesumeanalyzer.ats.dto.JobDescriptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "job-description-service")
public interface JobDescriptionServiceClient {

    @GetMapping("/api/job-descriptions/{id}")
    JobDescriptionResponse getJobDescription(@PathVariable String id);
}