package com.airesumeanalyzer.ats.service.Impl;

import com.airesumeanalyzer.ats.client.ResumeServiceClient;
import com.airesumeanalyzer.ats.dto.ResumeResponse;
import com.airesumeanalyzer.ats.entity.AtsResult;
import com.airesumeanalyzer.ats.kafka.event.ResumeUploadedEvent;
import com.airesumeanalyzer.ats.repository.AtsResultRepository;
import com.airesumeanalyzer.ats.service.AtsService;
import org.springframework.stereotype.Service;
import com.airesumeanalyzer.ats.util.SkillExtractor;

import java.time.LocalDateTime;

@Service
public class AtsServiceImpl implements AtsService {

    private final AtsResultRepository atsResultRepository;
    private final ResumeServiceClient resumeServiceClient;
    private final SkillExtractor skillExtractor;

    public AtsServiceImpl(
            AtsResultRepository atsResultRepository,
            ResumeServiceClient resumeServiceClient,
            SkillExtractor skillExtractor) {

        this.atsResultRepository = atsResultRepository;
        this.resumeServiceClient = resumeServiceClient;
        this.skillExtractor = skillExtractor;
    }

    @Override
    public AtsResult analyzeResume(ResumeUploadedEvent event) {

        // Fetch resume from Resume Service
        ResumeResponse resume =
                resumeServiceClient.getResume(event.getResumeId());

        System.out.println("==================================");
        System.out.println("Resume Text:");
        System.out.println(resume.getExtractedText());
        System.out.println("==================================");

        // Extract skills from resume
        var matchedSkills = skillExtractor.extractSkills(resume.getExtractedText());

        // Print extracted skills
        System.out.println("----------------------------------");
        System.out.println("Matched Skills:");
        matchedSkills.forEach(System.out::println);
        System.out.println("----------------------------------");

        AtsResult atsResult = new AtsResult();

        atsResult.setResumeId(event.getResumeId());
        atsResult.setUserId(event.getUserId());

        // Dummy ATS score
        atsResult.setAtsScore(75);

        // Save extracted matched skills
        atsResult.setMatchedSkills(String.join(", ", matchedSkills));

        // Dummy missing skills
        atsResult.setMissingSkills("Docker, Kubernetes");

        // Dummy suggestions
        atsResult.setSuggestions("Add Docker and Kubernetes experience.");

        atsResult.setCreatedAt(LocalDateTime.now());

        return atsResultRepository.save(atsResult);
    }

    @Override
    public AtsResult getResultByResumeId(Long resumeId) {

        return atsResultRepository.findByResumeId(resumeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "ATS Result not found for Resume ID: " + resumeId
                        ));
    }
}