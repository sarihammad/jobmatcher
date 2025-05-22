package com.devign.jobmatcher.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devign.jobmatcher.candidate.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    
}
