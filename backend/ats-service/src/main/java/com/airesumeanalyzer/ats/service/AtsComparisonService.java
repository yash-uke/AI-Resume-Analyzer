package com.airesumeanalyzer.ats.service;

import com.airesumeanalyzer.ats.dto.AtsComparisonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtsComparisonService {

    public AtsComparisonResponse compareSkills(List<String> resumeSkills,
                                               List<String> jdSkills) {

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : jdSkills) {

            if (resumeSkills.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        double matchPercentage = 0;

        if (!jdSkills.isEmpty()) {
            matchPercentage =
                    (matchedSkills.size() * 100.0) / jdSkills.size();
        }

        return new AtsComparisonResponse(
                matchedSkills,
                missingSkills,
                matchPercentage
        );
    }
}