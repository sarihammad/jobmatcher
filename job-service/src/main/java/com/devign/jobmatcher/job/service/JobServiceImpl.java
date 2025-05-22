package com.devign.jobmatcher.job.service;

import com.devign.jobmatcher.job.dto.*;
import com.devign.jobmatcher.job.kafka.JobProducer;
import com.devign.jobmatcher.job.model.Job;
import com.devign.jobmatcher.job.repository.JobRepository;
import com.devign.jobmatcher.job.repository.JobSearchRepository;
import com.devign.jobmatcher.job.document.JobDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobSearchRepository searchRepository;
    private final JobProducer jobProducer;

    @Override
    public JobResponse createJob(JobRequest request) {
        Job job = Job.builder()
                .title(request.getTitle())
                .company(request.getCompany())
                .location(request.getLocation())
                .requiredSkills(request.getRequiredSkills())
                .minExperience(request.getMinExperience())
                .maxExperience(request.getMaxExperience())
                .build();

        Job saved = jobRepository.save(job);
        JobResponse response = mapToResponse(saved);

        jobProducer.sendJob(response);

        JobDocument document = JobDocument.builder()
                .id(response.getId().toString())
                .title(response.getTitle())
                .company(response.getCompany())
                .location(response.getLocation())
                .requiredSkills(response.getRequiredSkills())
                .minExperience(response.getMinExperience())
                .maxExperience(response.getMaxExperience())
                .build();

        searchRepository.save(document);

        return response;
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<JobDocument> searchBySkill(String skill) {
        return StreamSupport.stream(searchRepository.findAll().spliterator(), false)
                .filter(doc -> doc.getRequiredSkills().stream()
                        .anyMatch(s -> s.toLowerCase().contains(skill.toLowerCase())))
                .toList();
    }

    private JobResponse mapToResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .location(job.getLocation())
                .requiredSkills(job.getRequiredSkills())
                .minExperience(job.getMinExperience())
                .maxExperience(job.getMaxExperience())
                .build();
    }
}