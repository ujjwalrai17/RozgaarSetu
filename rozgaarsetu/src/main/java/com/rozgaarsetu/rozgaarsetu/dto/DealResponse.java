package com.rozgaarsetu.rozgaarsetu.dto;

import com.rozgaarsetu.rozgaarsetu.entity.DealStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DealResponse {
    private Long id;
    private Long jobId;
    private Long labourId;
    private String labourName;
    private DealStatus status;
}