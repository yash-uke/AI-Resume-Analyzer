package com.airesumeanalyzer.ats.service.Impl;

import com.airesumeanalyzer.ats.client.JobDescriptionServiceClient;
import com.airesumeanalyzer.ats.client.ResumeServiceClient;
import com.airesumeanalyzer.ats.dto.JobDescriptionResponse;
import com.airesumeanalyzer.ats.dto.ResumeResponse;
import com.airesumeanalyzer.ats.entity.AtsResult;
import com.airesumeanalyzer.ats.kafka.event.ResumeUploadedEvent;
import com.airesumeanalyzer.ats.repository.AtsResultRepository;
import com.airesumeanalyzer.ats.service.AtsService;
import com.airesumeanalyzer.ats.util.SkillExtractor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.time.LocalDateTime;

@Service
public class AtsServiceImpl implements AtsService {

    private final AtsResultRepository atsResultRepository;
    private final ResumeServiceClient resumeServiceClient;
    private final JobDescriptionServiceClient jobDescriptionServiceClient;
    private final SkillExtractor skillExtractor;

    public AtsServiceImpl(
            AtsResultRepository atsResultRepository,
            ResumeServiceClient resumeServiceClient,
            JobDescriptionServiceClient jobDescriptionServiceClient,
            SkillExtractor skillExtractor) {

        this.atsResultRepository = atsResultRepository;
        this.resumeServiceClient = resumeServiceClient;
        this.jobDescriptionServiceClient = jobDescriptionServiceClient;
        this.skillExtractor = skillExtractor;
    }

    @Override
    public AtsResult analyzeResume(ResumeUploadedEvent event) {

        // Fetch Resume
        ResumeResponse resume =
                resumeServiceClient.getResume(event.getResumeId());

        // Fetch Job Description
        JobDescriptionResponse jobDescription =
                jobDescriptionServiceClient.getJobDescription(event.getJobDescriptionId());

        System.out.println("==================================");
        System.out.println("Resume Text:");
        System.out.println(resume.getExtractedText());
        System.out.println("==================================");

        System.out.println("==================================");
        System.out.println("Job Description Text:");
        System.out.println(jobDescription.getExtractedText());
        System.out.println("==================================");

        // Extract Resume Skills
        List<String> resumeSkills =
                skillExtractor.extractSkills(resume.getExtractedText());

// Extract Job Description Skills
        List<String> jdSkills =
                skillExtractor.extractSkills(jobDescription.getExtractedText());

        System.out.println("----------------------------------");
        System.out.println("Resume Skills:");
        resumeSkills.forEach(System.out::println);
        System.out.println("----------------------------------");

        System.out.println("----------------------------------");
        System.out.println("Job Description Skills:");
        jdSkills.forEach(System.out::println);
        System.out.println("----------------------------------");
        AtsResult atsResult = new AtsResult();

        atsResult.setResumeId(event.getResumeId());
        atsResult.setUserId(event.getUserId());

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : jdSkills) {

            if (resumeSkills.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        int atsScore = 0;

        if (!jdSkills.isEmpty()) {
            atsScore = (matchedSkills.size() * 100) / jdSkills.size();
        }

        atsResult.setAtsScore(atsScore);

        atsResult.setMatchedSkills(
                String.join(", ", matchedSkills));

        atsResult.setMissingSkills(
                String.join(", ", missingSkills));

        if (missingSkills.isEmpty()) {

            atsResult.setSuggestions(
                    "Excellent! Your resume matches the job description.");

        } else {

            atsResult.setSuggestions(
                    "Consider adding these skills: "
                            + String.join(", ", missingSkills));
        }

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