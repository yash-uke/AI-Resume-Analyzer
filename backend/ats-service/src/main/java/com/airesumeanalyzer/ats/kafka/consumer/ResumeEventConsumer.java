package com.airesumeanalyzer.ats.kafka.consumer;

import com.airesumeanalyzer.ats.entity.AtsResult;
import com.airesumeanalyzer.ats.kafka.event.ResumeUploadedEvent;
import com.airesumeanalyzer.ats.service.AtsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ResumeEventConsumer {

    private final ObjectMapper objectMapper;
    private final AtsService atsService;

    public ResumeEventConsumer(
            ObjectMapper objectMapper,
            AtsService atsService) {

        this.objectMapper = objectMapper;
        this.atsService = atsService;
    }

    @KafkaListener(
            topics = "resume-uploaded",
            groupId = "ats-group"
    )
    public void consume(String message) {

        try {

            ResumeUploadedEvent event =
                    objectMapper.readValue(message, ResumeUploadedEvent.class);

            System.out.println("==================================");
            System.out.println("Resume Event Received");
            System.out.println(event);

            AtsResult result = atsService.analyzeResume(event);

            System.out.println("----------------------------------");
            System.out.println("ATS Result Saved");
            System.out.println("ATS Score : " + result.getAtsScore());
            System.out.println("----------------------------------");

            System.out.println("==================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}