package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.*;
import com.rozgaarsetu.rozgaarsetu.entity.*;
import com.rozgaarsetu.rozgaarsetu.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final DealRepository dealRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public ApplicationResponse apply(ApplyJobRequest request) {

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (job.getStatus() == JobStatus.CLOSED) {
            throw new RuntimeException("Job is closed");
        }

        if (dealRepository.existsByJobId(job.getId())) {
            throw new RuntimeException("Deal already locked for this job");
        }

        User labour = userRepository.findById(request.getLabourId())
                .orElseThrow(() -> new RuntimeException("Labour not found"));

        if (labour.getRole() != Role.LABOUR) {
            throw new RuntimeException("Only LABOUR can apply");
        }

        if (applicationRepository.existsByJobIdAndLabourId(job.getId(), labour.getId())) {
            throw new RuntimeException("Already applied");
        }

        Application saved = applicationRepository.save(
                Application.builder()
                        .job(job)
                        .labour(labour)
                        .status(ApplicationStatus.APPLIED)
                        .build()
        );

        return map(saved);
    }

    @Override
    public List<ApplicationResponse> listApplicants(Long jobId, Long employerId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getEmployer().getId().equals(employerId)) {
            throw new RuntimeException("You are not the owner of this job");
        }

        return applicationRepository.findByJobId(jobId)
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DealResponse accept(AcceptApplicationRequest request) {

        Application app = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Job job = app.getJob();

        if (!job.getEmployer().getId().equals(request.getEmployerId())) {
            throw new RuntimeException("You are not the owner of this job");
        }

        if (job.getStatus() == JobStatus.CLOSED || dealRepository.existsByJobId(job.getId())) {
            throw new RuntimeException("Job already locked/closed");
        }

        // Accept this application
        app.setStatus(ApplicationStatus.ACCEPTED);
        applicationRepository.save(app);

        // Reject all other applications for this job
        List<Application> others = applicationRepository.findByJobId(job.getId());
        for (Application a : others) {
            if (!a.getId().equals(app.getId())) {
                a.setStatus(ApplicationStatus.REJECTED);
            }
        }
        applicationRepository.saveAll(others);

        // Close job
        job.setStatus(JobStatus.CLOSED);
        jobRepository.save(job);

        // Create Deal
        Deal deal = Deal.builder()
                .job(job)
                .labour(app.getLabour())
                .status(DealStatus.LOCKED)
                .build();

        Deal savedDeal = dealRepository.save(deal);

        return DealResponse.builder()
                .id(savedDeal.getId())
                .jobId(savedDeal.getJob().getId())
                .labourId(savedDeal.getLabour().getId())
                .labourName(savedDeal.getLabour().getFullName())
                .status(savedDeal.getStatus())
                .build();
    }

    private ApplicationResponse map(Application app) {
        return ApplicationResponse.builder()
                .id(app.getId())
                .jobId(app.getJob().getId())
                .labourId(app.getLabour().getId())
                .labourName(app.getLabour().getFullName())
                .status(app.getStatus())
                .build();
    }
}