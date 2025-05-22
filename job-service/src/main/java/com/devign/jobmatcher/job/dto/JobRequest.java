package com.devign.jobmatcher.job.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String company;

    @NotBlank
    private String location;

    @Size(min = 1)
    private List<String> requiredSkills;

    @Min(0)
    private int minExperience;

    @Min(0)
    private int maxExperience;
}