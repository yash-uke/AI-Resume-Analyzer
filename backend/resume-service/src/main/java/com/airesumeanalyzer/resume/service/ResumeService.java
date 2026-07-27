package com.airesumeanalyzer.resume.service;

import com.airesumeanalyzer.resume.dto.ResumeRequest;
import com.airesumeanalyzer.resume.dto.ResumeResponse;

public interface ResumeService {

    ResumeResponse uploadResume(ResumeRequest request);

}