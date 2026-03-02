package com.rozgaarsetu.rozgaarsetu.controller;

import com.rozgaarsetu.rozgaarsetu.dto.*;
import com.rozgaarsetu.rozgaarsetu.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ApplicationResponse apply(@Valid @RequestBody ApplyJobRequest request) {
        return applicationService.apply(request);
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationResponse> listApplicants(
            @PathVariable Long jobId,
            @RequestParam Long employerId
    ) {
        return applicationService.listApplicants(jobId, employerId);
    }

    @PostMapping("/accept")
    public DealResponse accept(@Valid @RequestBody AcceptApplicationRequest request) {
        return applicationService.accept(request);
    }
}