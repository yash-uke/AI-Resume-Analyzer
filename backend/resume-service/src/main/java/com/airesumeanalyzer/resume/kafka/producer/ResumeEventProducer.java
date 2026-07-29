package com.airesumeanalyzer.resume.kafka.producer;

import com.airesumeanalyzer.resume.kafka.event.ResumeUploadedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "resume-uploaded";

    public void publishResumeUploadedEvent(ResumeUploadedEvent event) {

        try {

            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(TOPIC, json);

            System.out.println("Resume Uploaded Event Sent : " + json);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}