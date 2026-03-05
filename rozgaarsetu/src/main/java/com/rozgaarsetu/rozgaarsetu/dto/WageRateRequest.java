package com.rozgaarsetu.rozgaarsetu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WageRateRequest {

    @NotBlank
    private String city;

    @NotBlank
    private String skill;

    @Positive
    private int perDayRate;

    @Positive
    private int overtimePerHourRate;
}