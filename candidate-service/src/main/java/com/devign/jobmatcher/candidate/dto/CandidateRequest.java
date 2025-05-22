package com.devign.jobmatcher.candidate.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateRequest {
    
    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String location;

    @Size(min = 1)
    private List<String> skills;

    @Min(0)
    private int yearsOfExperience;
}