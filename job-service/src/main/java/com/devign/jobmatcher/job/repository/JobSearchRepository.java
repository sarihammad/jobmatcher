package com.devign.jobmatcher.job.repository;

import com.devign.jobmatcher.job.document.JobDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JobSearchRepository extends ElasticsearchRepository<JobDocument, String> {
}