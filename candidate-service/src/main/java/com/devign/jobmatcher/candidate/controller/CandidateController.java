package com.devign.jobmatcher.candidate.controller;

import com.devign.jobmatcher.candidate.document.CandidateDocument;
import com.devign.jobmatcher.candidate.dto.*;
import com.devign.jobmatcher.candidate.service.CandidateService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody @Valid CandidateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.createCandidate(request));
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CandidateDocument>> searchBySkill(@RequestParam String skill) {
        List<CandidateDocument> results = candidateService.searchBySkill(skill);
        return ResponseEntity.ok(results);
    }
}