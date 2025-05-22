package com.devign.jobmatcher.candidate.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "candidates")
public class CandidateDocument {

    @Id
    private String id;

    private String fullName;
    private String email;
    private String location;
    private List<String> skills;
    private int yearsOfExperience;
}