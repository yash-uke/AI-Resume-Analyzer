package com.airesumeanalyzer.ats.repository;

import com.airesumeanalyzer.ats.entity.AtsResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtsResultRepository extends JpaRepository<AtsResult, Long> {

    Optional<AtsResult> findByResumeId(Long resumeId);

}