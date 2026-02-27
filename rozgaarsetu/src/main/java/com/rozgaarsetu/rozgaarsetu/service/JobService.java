package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.JobRequest;
import com.rozgaarsetu.rozgaarsetu.dto.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(JobRequest request);

    List<JobResponse> getJobs(String city);
}