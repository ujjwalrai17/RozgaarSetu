package com.rozgaarsetu.rozgaarsetu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteDealRequest {

    @NotNull
    private Long dealId;

    @NotNull
    private Long employerId;

    @Positive
    private int finalDays;

    @PositiveOrZero
    private int overtimeHours;

    @Positive
    private int paidAmount;
}