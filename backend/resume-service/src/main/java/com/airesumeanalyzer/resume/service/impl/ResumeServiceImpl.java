package com.airesumeanalyzer.resume.service.impl;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;
import com.airesumeanalyzer.resume.entity.Resume;
import com.airesumeanalyzer.resume.exception.ResumeNotFoundException;
import com.airesumeanalyzer.resume.kafka.event.ResumeUploadedEvent;
import com.airesumeanalyzer.resume.kafka.producer.ResumeEventProducer;
import com.airesumeanalyzer.resume.repository.ResumeRepository;
import com.airesumeanalyzer.resume.service.FileStorageService;
import com.airesumeanalyzer.resume.service.ResumeParserService;
import com.airesumeanalyzer.resume.service.ResumeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final FileStorageService fileStorageService;
    private final ResumeParserService resumeParserService;
    private final ResumeEventProducer resumeEventProducer;

    public ResumeServiceImpl(
            ResumeRepository resumeRepository,
            FileStorageService fileStorageService,
            ResumeParserService resumeParserService,
            ResumeEventProducer resumeEventProducer) {

        this.resumeRepository = resumeRepository;
        this.fileStorageService = fileStorageService;
        this.resumeParserService = resumeParserService;
        this.resumeEventProducer = resumeEventProducer;
    }

    @Override
    public ResumeResponse uploadResume(Long userId, MultipartFile file) {

        String filePath = fileStorageService.storeFile(file);

        File resumeFile = new File(filePath);
        String extractedText = resumeParserService.extractText(resumeFile);

        String fileName = Paths.get(filePath).getFileName().toString();

        String fileType = "";
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            fileType = fileName.substring(index + 1);
        }

        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setFileName(fileName);
        resume.setFileType(fileType);
        resume.setFileUrl(filePath);
        resume.setUploadDate(LocalDateTime.now());
        resume.setStatus("UPLOADED");
        resume.setExtractedText(extractedText);

        Resume savedResume = resumeRepository.save(resume);

        ResumeUploadedEvent event = new ResumeUploadedEvent(
                savedResume.getId(),
                savedResume.getUserId(),
                savedResume.getFileName()
        );

        resumeEventProducer.publishResumeUploadedEvent(event);

        ResumeResponse response = new ResumeResponse();
        response.setId(savedResume.getId());
        response.setUserId(savedResume.getUserId());
        response.setFileName(savedResume.getFileName());
        response.setFileType(savedResume.getFileType());
        response.setFileUrl(savedResume.getFileUrl());
        response.setUploadDate(savedResume.getUploadDate());
        response.setStatus(savedResume.getStatus());
        response.setExtractedText(savedResume.getExtractedText());

        return response;
    }

    @Override
    public List<ResumeResponse> getAllResumes() {

        return resumeRepository.findAll()
                .stream()
                .map(resume -> {

                    ResumeResponse response = new ResumeResponse();

                    response.setId(resume.getId());
                    response.setUserId(resume.getUserId());
                    response.setFileName(resume.getFileName());
                    response.setFileType(resume.getFileType());
                    response.setFileUrl(resume.getFileUrl());
                    response.setUploadDate(resume.getUploadDate());
                    response.setStatus(resume.getStatus());
                    response.setExtractedText(resume.getExtractedText());

                    return response;
                })
                .toList();
    }

    @Override
    public ResumeResponse getResumeById(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume not found with ID: " + id));

        ResumeResponse response = new ResumeResponse();

        response.setId(resume.getId());
        response.setUserId(resume.getUserId());
        response.setFileName(resume.getFileName());
        response.setFileType(resume.getFileType());
        response.setFileUrl(resume.getFileUrl());
        response.setUploadDate(resume.getUploadDate());
        response.setStatus(resume.getStatus());
        response.setExtractedText(resume.getExtractedText());

        return response;
    }

    @Override
    public void deleteResume(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() ->
                        new ResumeNotFoundException("Resume not found with ID: " + id));

        fileStorageService.deleteFile(resume.getFileUrl());

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

        ResumeResponse response = new ResumeResponse();

        response.setId(updatedResume.getId());
        response.setUserId(updatedResume.getUserId());
        response.setFileName(updatedResume.getFileName());
        response.setFileType(updatedResume.getFileType());
        response.setFileUrl(updatedResume.getFileUrl());
        response.setUploadDate(updatedResume.getUploadDate());
        response.setStatus(updatedResume.getStatus());
        response.setExtractedText(updatedResume.getExtractedText());

        return response;
    }
}