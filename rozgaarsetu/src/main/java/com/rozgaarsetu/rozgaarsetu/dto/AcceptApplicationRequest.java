package com.rozgaarsetu.rozgaarsetu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptApplicationRequest {
    @NotNull
    private Long applicationId;

    @NotNull
    private Long employerId; // to validate owner of job
}