package com.airesumeanalyzer.ats.dto;

import java.util.List;

public class AtsComparisonResponse {

    private List<String> matchedSkills;
    private List<String> missingSkills;
    private double matchPercentage;

    public AtsComparisonResponse() {
    }

    public AtsComparisonResponse(List<String> matchedSkills,
                                 List<String> missingSkills,
                                 double matchPercentage) {
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}