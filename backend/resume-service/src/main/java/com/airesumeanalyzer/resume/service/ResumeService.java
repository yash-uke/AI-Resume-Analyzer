package com.airesumeanalyzer.resume.service;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {

    ResumeResponse uploadResume(Long userId, MultipartFile file);

    List<ResumeResponse> getAllResumes();

    ResumeResponse getResumeById(Long id);

    void deleteResume(Long id);

    ResumeResponse updateResume(Long id, ResumeRequest request);
}