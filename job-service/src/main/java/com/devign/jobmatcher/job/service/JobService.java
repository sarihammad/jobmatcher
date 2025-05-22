package com.devign.jobmatcher.job.service;

import com.devign.jobmatcher.job.document.JobDocument;
import com.devign.jobmatcher.job.dto.*;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest request);
    List<JobResponse> getAllJobs();
    List<JobDocument> searchBySkill(String skill);
}