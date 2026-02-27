package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.JobRequest;
import com.rozgaarsetu.rozgaarsetu.dto.JobResponse;
import com.rozgaarsetu.rozgaarsetu.entity.Job;
import com.rozgaarsetu.rozgaarsetu.entity.User;
import com.rozgaarsetu.rozgaarsetu.repository.JobRepository;
import com.rozgaarsetu.rozgaarsetu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public JobResponse createJob(JobRequest request) {

        User employer = userRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .city(request.getCity())
                .locationText(request.getLocationText())
                .numberOfDays(request.getNumberOfDays())
                .requiredSkill(request.getRequiredSkill())
                .employer(employer)
                .build();

        Job saved = jobRepository.save(job);

        return map(saved);
    }

    @Override
    public List<JobResponse> getJobs(String city) {

        List<Job> jobs = (city == null)
                ? jobRepository.findAll()
                : jobRepository.findByCityIgnoreCase(city);

        return jobs.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private JobResponse map(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .city(job.getCity())
                .locationText(job.getLocationText())
                .numberOfDays(job.getNumberOfDays())
                .requiredSkill(job.getRequiredSkill())
                .employerName(job.getEmployer().getFullName())
                .build();
    }
}