package com.rozgaarsetu.rozgaarsetu.controller;

import com.rozgaarsetu.rozgaarsetu.dto.JobRequest;
import com.rozgaarsetu.rozgaarsetu.dto.JobResponse;
import com.rozgaarsetu.rozgaarsetu.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public JobResponse createJob(@Valid @RequestBody JobRequest request) {
        return jobService.createJob(request);
    }

    @GetMapping
    public List<JobResponse> getJobs(
            @RequestParam(required = false) String city) {
        return jobService.getJobs(city);
    }
}