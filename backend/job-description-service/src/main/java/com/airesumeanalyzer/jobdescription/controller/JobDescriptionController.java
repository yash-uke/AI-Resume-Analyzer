package com.airesumeanalyzer.jobdescription.controller;

import com.airesumeanalyzer.jobdescription.dto.JobDescriptionResponse;
import com.airesumeanalyzer.jobdescription.service.JobDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/job-descriptions")
public class JobDescriptionController {

    private final JobDescriptionService jobDescriptionService;

    public JobDescriptionController(JobDescriptionService jobDescriptionService) {
        this.jobDescriptionService = jobDescriptionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<JobDescriptionResponse> uploadJobDescription(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("companyName") String companyName) throws IOException {

        JobDescriptionResponse response =
                jobDescriptionService.uploadJobDescription(file, title, companyName);

        return ResponseEntity.ok(response);
    }
}