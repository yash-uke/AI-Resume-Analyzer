package com.airesumeanalyzer.interview.service.impl;

import com.airesumeanalyzer.interview.client.AtsServiceClient;
import com.airesumeanalyzer.interview.client.JobDescriptionServiceClient;
import com.airesumeanalyzer.interview.client.ResumeServiceClient;
import com.airesumeanalyzer.interview.dto.AnswerRequest;
import com.airesumeanalyzer.interview.dto.AtsResultResponse;
import com.airesumeanalyzer.interview.dto.ResumeResponse;
import com.airesumeanalyzer.interview.entity.InterviewResult;
import com.airesumeanalyzer.interview.repository.InterviewResultRepository;
import com.airesumeanalyzer.interview.service.InterviewService;
import com.airesumeanalyzer.interview.service.ai.GeminiService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewResultRepository repository;
    private final ResumeServiceClient resumeClient;
    private final AtsServiceClient atsClient;
    private final JobDescriptionServiceClient jdClient;
    private final GeminiService geminiService;

    public InterviewServiceImpl(
            InterviewResultRepository repository,
            ResumeServiceClient resumeClient,
            AtsServiceClient atsClient,
            JobDescriptionServiceClient jdClient,
            GeminiService geminiService) {

        this.repository = repository;
        this.resumeClient = resumeClient;
        this.atsClient = atsClient;
        this.jdClient = jdClient;
        this.geminiService = geminiService;
    }

    @Override
    public InterviewResult generateInterview(Long resumeId) {

        // Fetch Resume
        ResumeResponse resume =
                resumeClient.getResume(resumeId);

        // Fetch ATS Result
        AtsResultResponse ats =
                atsClient.getAtsResult(resumeId);

        // AI Prompt
        String prompt = """
You are an expert Java Full Stack Interviewer.

Candidate Resume:
%s

ATS Score:
%d

Matched Skills:
%s

Missing Skills:
%s

                Generate interview questions in EXACTLY this format.
                
                                    Technical Questions
                                    1.
                                    2.
                                    3.
                                    4.
                                    5.
                
                                    Coding Questions
                                    1.
                                    2.
                                    3.
                                    4.
                                    5.
                
                                    HR Questions
                                    1.
                                    2.
                                    3.
                                    4.
                                    5.
                
                                    Do not add explanations.
                                    Do not use markdown.
                                    Return only plain text.

Return only the questions.
""".formatted(
                resume.getExtractedText(),
                ats.getAtsScore(),
                ats.getMatchedSkills(),
                ats.getMissingSkills()
        );

        String questions = geminiService.generateQuestions(prompt);

        String technicalQuestions = "";
        String codingQuestions = "";
        String hrQuestions = "";

        String[] sections = questions.split("HR Questions");

        if (sections.length >= 2) {

            String[] firstHalf = sections[0].split("Coding Questions");

            if (firstHalf.length >= 2) {

                technicalQuestions = firstHalf[0]
                        .replace("Technical Questions", "")
                        .trim();

                codingQuestions = firstHalf[1].trim();
            }

            hrQuestions = sections[1].trim();
        }

        InterviewResult interview = repository
                .findByResumeId(resumeId)
                .orElse(new InterviewResult());

        interview.setResumeId(resumeId);
        interview.setTechnicalQuestions(technicalQuestions);
        interview.setCodingQuestions(codingQuestions);
        interview.setHrQuestions(hrQuestions);
        interview.setCreatedAt(LocalDateTime.now());

        return repository.save(interview);
    }

    @Override
    public InterviewResult getInterview(Long resumeId) {

        return repository.findByResumeId(resumeId)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found"));
    }

    @Override
    public List<InterviewResult> getAllInterviews() {

        return repository.findAll();
    }

    @Override
    public String evaluateAnswer(AnswerRequest request) {

        String prompt = """
You are an expert Java interviewer.

Question:
%s

Candidate Answer:
%s

Evaluate the answer.

Return:

1. Score out of 10
2. Strengths
3. Weaknesses
4. Correct Answer
5. Improvement Tips

""".formatted(
                request.getQuestion(),
                request.getAnswer()
        );

        return geminiService.generateQuestions(prompt);
    }
}