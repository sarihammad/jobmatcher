package com.devign.jobmatcher.job.controller;

import com.devign.jobmatcher.job.dto.*;
import com.devign.jobmatcher.job.document.JobDocument;
import com.devign.jobmatcher.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestBody @Valid JobRequest request) {
        return ResponseEntity.ok(jobService.createJob(request));
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobDocument>> searchBySkill(@RequestParam String skill) {
        return ResponseEntity.ok(jobService.searchBySkill(skill));
    }
}