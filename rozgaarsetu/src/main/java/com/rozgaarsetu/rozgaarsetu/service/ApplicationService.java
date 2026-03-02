package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.*;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse apply(ApplyJobRequest request);
    List<ApplicationResponse> listApplicants(Long jobId, Long employerId);
    DealResponse accept(AcceptApplicationRequest request);
}