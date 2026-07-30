package com.airesumeanalyzer.jobdescription.repository;

import com.airesumeanalyzer.jobdescription.entity.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, String> {
}