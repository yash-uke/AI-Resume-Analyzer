package com.airesumeanalyzer.interview.controller;

import com.airesumeanalyzer.interview.service.ai.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class GeminiTestController {

    private final GeminiService geminiService;

    public GeminiTestController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/gemini")
    public String testGemini() {

        return geminiService.generateQuestions(
                "Say hello in one sentence."
        );
    }
}