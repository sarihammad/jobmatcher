package com.devign.jobmatcher.candidate.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponse {
    private Long id;
    private String fullName;
    private String email;
    private String location;
    private List<String> skills;
    private int yearsOfExperience;
}