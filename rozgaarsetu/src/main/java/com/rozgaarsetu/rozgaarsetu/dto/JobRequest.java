package com.rozgaarsetu.rozgaarsetu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String city;

    @NotBlank
    private String locationText;

    @Positive
    private int numberOfDays;

    @NotBlank
    private String requiredSkill;

    private Long employerId;
}