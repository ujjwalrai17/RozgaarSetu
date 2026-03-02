package com.rozgaarsetu.rozgaarsetu.repository;

import com.rozgaarsetu.rozgaarsetu.entity.Application;
import com.rozgaarsetu.rozgaarsetu.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobId(Long jobId);
    boolean existsByJobIdAndLabourId(Long jobId, Long labourId);
    List<Application> findByJobIdAndStatus(Long jobId, ApplicationStatus status);
}