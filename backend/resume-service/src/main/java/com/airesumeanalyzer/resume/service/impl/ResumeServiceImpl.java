package com.airesumeanalyzer.resume.service.impl;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;
import com.airesumeanalyzer.resume.entity.Resume;
import com.airesumeanalyzer.resume.repository.ResumeRepository;
import com.airesumeanalyzer.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public ResumeResponse uploadResume(ResumeRequest request) {

        Resume resume = Resume.builder()
                .userId(request.getUserId())
                .fileName(request.getFileName())
                .fileType(request.getFileType())
                .fileUrl(request.getFileUrl())
                .uploadDate(LocalDateTime.now())
                .status("UPLOADED")
                .build();

        Resume savedResume = resumeRepository.save(resume);

        return ResumeResponse.builder()
                .id(savedResume.getId())
                .userId(savedResume.getUserId())
                .fileName(savedResume.getFileName())
                .fileType(savedResume.getFileType())
                .fileUrl(savedResume.getFileUrl())
                .uploadDate(savedResume.getUploadDate())
                .status(savedResume.getStatus())
                .build();
    }
}