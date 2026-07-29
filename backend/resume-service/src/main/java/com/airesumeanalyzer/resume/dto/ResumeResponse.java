package com.airesumeanalyzer.resume.dto;

import java.time.LocalDateTime;

public class ResumeResponse {

    private Long id;
    private Long userId;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private LocalDateTime uploadDate;
    private String status;
    private String extractedText;

    // No-argument constructor
    public ResumeResponse() {
    }

    // All-argument constructor
    public ResumeResponse(Long id,
                          Long userId,
                          String fileName,
                          String fileType,
                          String fileUrl,
                          LocalDateTime uploadDate,
                          String status,
                          String extractedText) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
        this.uploadDate = uploadDate;
        this.status = status;
        this.extractedText = extractedText;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }
}