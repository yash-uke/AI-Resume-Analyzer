package com.airesumeanalyzer.resume.controller;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;
import com.airesumeanalyzer.resume.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResumeResponse uploadResume(
            @Valid @RequestBody ResumeRequest request) {

        return resumeService.uploadResume(request);
    }

    @GetMapping
    public List<ResumeResponse> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResumeResponse getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteResume(@PathVariable Long id) {

        resumeService.deleteResume(id);

        return "Resume deleted successfully";
    }

    @PutMapping("/{id}")
    public ResumeResponse updateResume(
            @PathVariable Long id,
            @Valid @RequestBody ResumeRequest request) {

        return resumeService.updateResume(id, request);
    }
}