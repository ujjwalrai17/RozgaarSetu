package com.rozgaarsetu.rozgaarsetu.repository;

import com.rozgaarsetu.rozgaarsetu.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsByJobId(Long jobId);
}