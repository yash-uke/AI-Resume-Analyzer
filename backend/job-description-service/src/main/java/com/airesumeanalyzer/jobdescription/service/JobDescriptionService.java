package com.airesumeanalyzer.jobdescription.service;

import com.airesumeanalyzer.jobdescription.dto.JobDescriptionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface JobDescriptionService {

    JobDescriptionResponse uploadJobDescription(
            MultipartFile file,
            String title,
            String companyName
    ) throws IOException;

    JobDescriptionResponse getJobDescriptionById(String id);
}