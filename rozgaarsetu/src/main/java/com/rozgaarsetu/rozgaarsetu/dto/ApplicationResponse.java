package com.rozgaarsetu.rozgaarsetu.dto;

import com.rozgaarsetu.rozgaarsetu.entity.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationResponse {
    private Long id;
    private Long jobId;
    private Long labourId;
    private String labourName;
    private ApplicationStatus status;
}