package com.rozgaarsetu.rozgaarsetu.controller;

import com.rozgaarsetu.rozgaarsetu.dto.CompleteDealRequest;
import com.rozgaarsetu.rozgaarsetu.dto.DealResponse;
import com.rozgaarsetu.rozgaarsetu.service.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @PostMapping("/complete")
    public DealResponse complete(@Valid @RequestBody CompleteDealRequest request) {
        return dealService.complete(request);
    }
}