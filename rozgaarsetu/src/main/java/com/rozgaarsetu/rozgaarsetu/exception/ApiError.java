package com.rozgaarsetu.rozgaarsetu.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiError {
    private String message;
}
