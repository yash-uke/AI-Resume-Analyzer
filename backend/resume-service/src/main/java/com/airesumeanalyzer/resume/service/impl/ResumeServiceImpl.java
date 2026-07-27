package com.airesumeanalyzer.resume.service.impl;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;
import com.airesumeanalyzer.resume.entity.Resume;
import com.airesumeanalyzer.resume.exception.ResumeNotFoundException;
import com.airesumeanalyzer.resume.repository.ResumeRepository;
import com.airesumeanalyzer.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    @Override
    public List<ResumeResponse> getAllResumes() {

        return resumeRepository.findAll()
                .stream()
                .map(resume -> ResumeResponse.builder()
                        .id(resume.getId())
                        .userId(resume.getUserId())
                        .fileName(resume.getFileName())
                        .fileType(resume.getFileType())
                        .fileUrl(resume.getFileUrl())
                        .uploadDate(resume.getUploadDate())
                        .status(resume.getStatus())
                        .build())
                .toList();
    }
    @Override
    public ResumeResponse getResumeById(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume not found with ID: " + id));

        return ResumeResponse.builder()
                .id(resume.getId())
                .userId(resume.getUserId())
                .fileName(resume.getFileName())
                .fileType(resume.getFileType())
                .fileUrl(resume.getFileUrl())
                .uploadDate(resume.getUploadDate())
                .status(resume.getStatus())
                .build();
    }

    @Override
    public void deleteResume(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume not found with ID: " + id));

        resumeRepository.delete(resume);
    }

    @Override
    public ResumeResponse updateResume(Long id, ResumeRequest request) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume not found with ID: " + id));

        resume.setUserId(request.getUserId());
        resume.setFileName(request.getFileName());
        resume.setFileType(request.getFileType());
        resume.setFileUrl(request.getFileUrl());

        Resume updatedResume = resumeRepository.save(resume);

        return ResumeResponse.builder()
                .id(updatedResume.getId())
                .userId(updatedResume.getUserId())
                .fileName(updatedResume.getFileName())
                .fileType(updatedResume.getFileType())
                .fileUrl(updatedResume.getFileUrl())
                .uploadDate(updatedResume.getUploadDate())
                .status(updatedResume.getStatus())
                .build();
    }
}