package com.airesumeanalyzer.ats.controller;

import com.airesumeanalyzer.ats.entity.AtsResult;
import com.airesumeanalyzer.ats.repository.AtsResultRepository;
import com.airesumeanalyzer.ats.service.AtsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ats")
public class AtsController {

    private final AtsService atsService;
    private final AtsResultRepository atsResultRepository;

    public AtsController(AtsService atsService,
                         AtsResultRepository atsResultRepository) {

        this.atsService = atsService;
        this.atsResultRepository = atsResultRepository;
    }

    @GetMapping("/{resumeId}")
    public AtsResult getAtsResult(@PathVariable Long resumeId) {

        return atsService.getResultByResumeId(resumeId);
    }

    @GetMapping
    public List<AtsResult> getAllResults() {

        return atsResultRepository.findAll();
    }
}