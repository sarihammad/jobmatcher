package com.devign.jobmatcher.job.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "jobs")
public class JobDocument {
    @Id
    private String id;

    private String title;
    private String company;
    private String location;
    private List<String> requiredSkills;
    private int minExperience;
    private int maxExperience;
}