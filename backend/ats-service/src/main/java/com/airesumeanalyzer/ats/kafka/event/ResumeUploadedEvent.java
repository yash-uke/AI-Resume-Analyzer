package com.airesumeanalyzer.ats.kafka.event;

public class ResumeUploadedEvent {

    private Long resumeId;
    private Long userId;
    private String fileName;

    public ResumeUploadedEvent() {
    }

    public ResumeUploadedEvent(Long resumeId, Long userId, String fileName) {
        this.resumeId = resumeId;
        this.userId = userId;
        this.fileName = fileName;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
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

    @Override
    public String toString() {
        return "ResumeUploadedEvent{" +
                "resumeId=" + resumeId +
                ", userId=" + userId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}