package com.airesumeanalyzer.resume.kafka.producer;

import com.airesumeanalyzer.resume.kafka.event.ResumeUploadedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeEventProducer {

    private final KafkaTemplate<String, ResumeUploadedEvent> kafkaTemplate;

    private static final String TOPIC = "resume-uploaded";

    public void publishResumeUploadedEvent(ResumeUploadedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Resume Uploaded Event Sent : " + event);
    }
}