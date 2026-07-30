package com.airesumeanalyzer.resume.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeUploadedEvent {

    private Long resumeId;

    private Long userId;

    private String fileName;

    private String jobDescriptionId;
}