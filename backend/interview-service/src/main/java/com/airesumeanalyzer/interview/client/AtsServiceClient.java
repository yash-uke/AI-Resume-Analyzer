package com.airesumeanalyzer.interview.client;

import com.airesumeanalyzer.interview.dto.AtsResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ats-service")
public interface AtsServiceClient {

    @GetMapping("/api/ats/{resumeId}")
    AtsResultResponse getAtsResult(@PathVariable Long resumeId);

}