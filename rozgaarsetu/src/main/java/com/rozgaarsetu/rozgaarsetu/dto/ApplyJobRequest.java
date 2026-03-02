package com.rozgaarsetu.rozgaarsetu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyJobRequest {
    @NotNull
    private Long jobId;

    @NotNull
    private Long labourId;
}