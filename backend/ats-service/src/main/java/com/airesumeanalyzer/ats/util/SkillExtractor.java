package com.airesumeanalyzer.ats.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillExtractor {

    private static final String[] SKILLS = {

            "Java",
            "Spring Boot",
            "Spring",
            "Hibernate",
            "JPA",
            "MySQL",
            "PostgreSQL",
            "MongoDB",
            "Redis",
            "Kafka",
            "Docker",
            "Kubernetes",
            "AWS",
            "Azure",
            "GCP",
            "Angular",
            "React",
            "JavaScript",
            "TypeScript",
            "HTML",
            "CSS",
            "Microservices",
            "REST API",
            "REST",
            "Git",
            "Jenkins",
            "JUnit",
            "Mockito",
            "Maven",
            "Gradle",
            "Spring Security",
            "JWT",
            "OAuth2"
    };

    public List<String> extractSkills(String resumeText) {

        List<String> matchedSkills = new ArrayList<>();

        String text = resumeText.toLowerCase();

        for (String skill : SKILLS) {

            if (text.contains(skill.toLowerCase())) {

                matchedSkills.add(skill);
            }
        }

        return matchedSkills;
    }
}