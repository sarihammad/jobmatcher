package com.devign.jobmatcher.candidate.service;

import com.devign.jobmatcher.candidate.document.CandidateDocument;
import com.devign.jobmatcher.candidate.dto.*;
import com.devign.jobmatcher.candidate.kafka.CandidateProducer;
import com.devign.jobmatcher.candidate.model.Candidate;
import com.devign.jobmatcher.candidate.repository.CandidateRepository;
import com.devign.jobmatcher.candidate.repository.CandidateSearchRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateProducer candidateProducer;
    private final CandidateSearchRepository searchRepository;

    @Override
    public CandidateResponse createCandidate(CandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .location(request.getLocation())
                .skills(request.getSkills())
                .yearsOfExperience(request.getYearsOfExperience())
                .build();

        Candidate saved = candidateRepository.save(candidate);
        CandidateResponse response = mapToResponse(saved);
        
        candidateProducer.sendCandidate(response);

        CandidateDocument doc = CandidateDocument.builder()
                .id(saved.getId().toString())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .location(saved.getLocation())
                .skills(saved.getSkills())
                .yearsOfExperience(saved.getYearsOfExperience())
                .build();

        searchRepository.save(doc);

        return response;
    }

    @Override
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<CandidateDocument> searchBySkill(String skill) {
        return StreamSupport.stream(searchRepository.findAll().spliterator(), false)
                .filter(doc -> doc.getSkills().stream()
                        .anyMatch(s -> s.toLowerCase().contains(skill.toLowerCase())))
                .toList();
    }

    private CandidateResponse mapToResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .fullName(candidate.getFullName())
                .email(candidate.getEmail())
                .location(candidate.getLocation())
                .skills(candidate.getSkills())
                .yearsOfExperience(candidate.getYearsOfExperience())
                .build();
    }
}