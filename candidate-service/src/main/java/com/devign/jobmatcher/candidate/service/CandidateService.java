package com.devign.jobmatcher.candidate.service;

import com.devign.jobmatcher.candidate.document.CandidateDocument;
import com.devign.jobmatcher.candidate.dto.CandidateRequest;
import com.devign.jobmatcher.candidate.dto.CandidateResponse;

import java.util.List;

public interface CandidateService {
    CandidateResponse createCandidate(CandidateRequest request);
    List<CandidateResponse> getAllCandidates();
    List<CandidateDocument> searchBySkill(String skill);
}