package com.airesumeanalyzer.resume.service.impl;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;
import com.airesumeanalyzer.resume.entity.Resume;
import com.airesumeanalyzer.resume.exception.ResumeNotFoundException;
import com.airesumeanalyzer.resume.repository.ResumeRepository;
import com.airesumeanalyzer.resume.service.FileStorageService;
import com.airesumeanalyzer.resume.service.ResumeParserService;
import com.airesumeanalyzer.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import com.airesumeanalyzer.resume.kafka.event.ResumeUploadedEvent;
import com.airesumeanalyzer.resume.kafka.producer.ResumeEventProducer;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final FileStorageService fileStorageService;
    private final ResumeParserService resumeParserService;
    private final ResumeEventProducer resumeEventProducer;

    @Override
    public ResumeResponse uploadResume(Long userId, MultipartFile file) {

        // Store file locally
        String filePath = fileStorageService.storeFile(file);

        // Parse uploaded resume
        File resumeFile = new File(filePath);
        String extractedText = resumeParserService.extractText(resumeFile);

        // Print extracted text
        System.out.println(extractedText);

        // Extract filename
        String fileName = Paths.get(filePath)
                .getFileName()
                .toString();

        // Extract extension
        String fileType = "";

        int index = fileName.lastIndexOf(".");

        if (index != -1) {
            fileType = fileName.substring(index + 1);
        }

        // Save metadata
        Resume resume = Resume.builder()
                .userId(userId)
                .fileName(fileName)
                .fileType(fileType)
                .fileUrl(filePath)
                .uploadDate(LocalDateTime.now())
                .status("UPLOADED")
                .extractedText(extractedText)
                .build();

        Resume savedResume = resumeRepository.save(resume);

        ResumeUploadedEvent event = new ResumeUploadedEvent(
                savedResume.getId(),
                savedResume.getUserId(),
                savedResume.getFileName()
        );

        resumeEventProducer.publishResumeUploadedEvent(event);

        // Return response
        return ResumeResponse.builder()
                .id(savedResume.getId())
                .userId(savedResume.getUserId())
                .fileName(savedResume.getFileName())
                .fileType(savedResume.getFileType())
                .fileUrl(savedResume.getFileUrl())
                .uploadDate(savedResume.getUploadDate())
                .status(savedResume.getStatus())
                .extractedText(savedResume.getExtractedText())
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
                        .extractedText(resume.getExtractedText())
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
                .extractedText(resume.getExtractedText())
                .build();
    }

    @Override
    public void deleteResume(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume not found with ID: " + id));

        // Delete physical file
        fileStorageService.deleteFile(resume.getFileUrl());

        // Delete database record
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
                .extractedText(updatedResume.getExtractedText())
                .build();
    }
}