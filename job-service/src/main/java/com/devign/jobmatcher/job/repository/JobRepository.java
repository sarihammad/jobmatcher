package com.devign.jobmatcher.job.repository;

import com.devign.jobmatcher.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}