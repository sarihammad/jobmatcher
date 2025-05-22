package com.devign.jobmatcher.candidate.repository;

import com.devign.jobmatcher.candidate.document.CandidateDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CandidateSearchRepository extends ElasticsearchRepository<CandidateDocument, String> {
}