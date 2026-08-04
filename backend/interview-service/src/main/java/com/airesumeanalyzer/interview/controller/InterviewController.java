package com.airesumeanalyzer.interview.controller;

import com.airesumeanalyzer.interview.dto.AnswerRequest;
import com.airesumeanalyzer.interview.entity.InterviewResult;
import com.airesumeanalyzer.interview.service.InterviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/generate/{resumeId}")
    public InterviewResult generateInterview(
            @PathVariable Long resumeId) {

        return interviewService.generateInterview(resumeId);
    }

    @GetMapping("/{resumeId}")
    public InterviewResult getInterview(
            @PathVariable Long resumeId) {

        return interviewService.getInterview(resumeId);
    }

    @GetMapping
    public List<InterviewResult> getAllInterviews() {

        return interviewService.getAllInterviews();
    }

    @PostMapping("/evaluate")
    public String evaluateAnswer(@RequestBody AnswerRequest request) {

        return interviewService.evaluateAnswer(request);
    }
}