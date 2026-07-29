package com.airesumeanalyzer.resume.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResumeRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "File name is required")
    private String fileName;

    @NotBlank(message = "File type is required")
    private String fileType;

    @NotBlank(message = "File URL is required")
    private String fileUrl;

    // No-argument constructor
    public ResumeRequest() {
    }

    // All-argument constructor
    public ResumeRequest(Long userId, String fileName, String fileType, String fileUrl) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters

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
}