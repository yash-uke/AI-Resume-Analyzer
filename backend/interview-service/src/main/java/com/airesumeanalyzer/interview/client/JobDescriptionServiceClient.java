package com.airesumeanalyzer.interview.client;

import com.airesumeanalyzer.interview.dto.JobDescriptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "job-description-service")
public interface JobDescriptionServiceClient {

    @GetMapping("/api/job-descriptions/{id}")
    JobDescriptionResponse getJobDescription(@PathVariable String id);

}