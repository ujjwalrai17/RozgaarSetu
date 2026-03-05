package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.CompleteDealRequest;
import com.rozgaarsetu.rozgaarsetu.dto.DealResponse;

public interface DealService {
    DealResponse complete(CompleteDealRequest request);
}