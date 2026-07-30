package com.airesumeanalyzer.jobdescription.service.Impl;

import com.airesumeanalyzer.jobdescription.dto.JobDescriptionResponse;
import com.airesumeanalyzer.jobdescription.entity.JobDescription;
import com.airesumeanalyzer.jobdescription.repository.JobDescriptionRepository;
import com.airesumeanalyzer.jobdescription.service.JobDescriptionService;
import com.airesumeanalyzer.jobdescription.service.PdfExtractorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class JobDescriptionServiceImpl implements JobDescriptionService {

    private final JobDescriptionRepository repository;
    private final PdfExtractorService pdfExtractorService;

    public JobDescriptionServiceImpl(JobDescriptionRepository repository,
                                     PdfExtractorService pdfExtractorService) {
        this.repository = repository;
        this.pdfExtractorService = pdfExtractorService;
    }

    @Override
    public JobDescriptionResponse uploadJobDescription(
            MultipartFile file,
            String title,
            String companyName) throws IOException {

        String extractedText = pdfExtractorService.extractText(file);

        JobDescription jobDescription = new JobDescription();

        jobDescription.setTitle(title);
        jobDescription.setCompanyName(companyName);
        jobDescription.setFileName(file.getOriginalFilename());
        jobDescription.setFileType(file.getContentType());
        jobDescription.setExtractedText(extractedText);
        jobDescription.setUploadDate(LocalDateTime.now());

        JobDescription saved = repository.save(jobDescription);

        JobDescriptionResponse response = new JobDescriptionResponse();

        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setCompanyName(saved.getCompanyName());
        response.setFileName(saved.getFileName());
        response.setFileType(saved.getFileType());
        response.setExtractedText(saved.getExtractedText());
        response.setUploadDate(saved.getUploadDate());

        return response;
    }
}