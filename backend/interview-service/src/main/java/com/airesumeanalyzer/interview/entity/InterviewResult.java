package com.airesumeanalyzer.interview.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interview_results")
public class InterviewResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long resumeId;

    @Column(columnDefinition = "TEXT")
    private String technicalQuestions;

    @Column(columnDefinition = "TEXT")
    private String codingQuestions;

    @Column(columnDefinition = "TEXT")
    private String hrQuestions;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public InterviewResult() {
    }

    public InterviewResult(Long id,
                           Long resumeId,
                           String technicalQuestions,
                           String codingQuestions,
                           String hrQuestions,
                           LocalDateTime createdAt) {

        this.id = id;
        this.resumeId = resumeId;
        this.technicalQuestions = technicalQuestions;
        this.codingQuestions = codingQuestions;
        this.hrQuestions = hrQuestions;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getTechnicalQuestions() {
        return technicalQuestions;
    }

    public void setTechnicalQuestions(String technicalQuestions) {
        this.technicalQuestions = technicalQuestions;
    }

    public String getCodingQuestions() {
        return codingQuestions;
    }

    public void setCodingQuestions(String codingQuestions) {
        this.codingQuestions = codingQuestions;
    }

    public String getHrQuestions() {
        return hrQuestions;
    }

    public void setHrQuestions(String hrQuestions) {
        this.hrQuestions = hrQuestions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}