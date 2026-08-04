package com.airesumeanalyzer.interview.service;

import com.airesumeanalyzer.interview.dto.AnswerRequest;
import com.airesumeanalyzer.interview.entity.InterviewResult;

import java.util.List;

public interface InterviewService {

    InterviewResult generateInterview(Long resumeId);

    InterviewResult getInterview(Long resumeId);

    List<InterviewResult> getAllInterviews();

    String evaluateAnswer(AnswerRequest request);
}