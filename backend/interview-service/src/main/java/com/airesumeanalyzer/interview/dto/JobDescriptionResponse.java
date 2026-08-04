package com.airesumeanalyzer.interview.dto;

public class JobDescriptionResponse {

    private String id;
    private String extractedText;

    public JobDescriptionResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }
}