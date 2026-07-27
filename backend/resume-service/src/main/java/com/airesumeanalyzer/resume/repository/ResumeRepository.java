package com.airesumeanalyzer.resume.repository;

import com.airesumeanalyzer.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}