package com.devign.jobmatcher.job.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String company;
    private String location;
    private List<String> requiredSkills;
    private int minExperience;
    private int maxExperience;
}