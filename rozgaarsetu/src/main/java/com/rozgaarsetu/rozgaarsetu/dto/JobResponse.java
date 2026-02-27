package com.rozgaarsetu.rozgaarsetu.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String city;
    private String locationText;
    private int numberOfDays;
    private String requiredSkill;
    private String employerName;
}