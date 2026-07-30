package com.airesumeanalyzer.ats.service;

import com.airesumeanalyzer.ats.entity.AtsResult;
import com.airesumeanalyzer.ats.kafka.event.ResumeUploadedEvent;

public interface AtsService {

    AtsResult analyzeResume(ResumeUploadedEvent event);

    AtsResult getResultByResumeId(Long resumeId);

}