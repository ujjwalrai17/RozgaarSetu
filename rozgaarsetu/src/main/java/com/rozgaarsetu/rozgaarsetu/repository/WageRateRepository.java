package com.rozgaarsetu.rozgaarsetu.repository;

import com.rozgaarsetu.rozgaarsetu.entity.WageRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WageRateRepository extends JpaRepository<WageRate, Long> {
    Optional<WageRate> findByCityIgnoreCaseAndSkillIgnoreCase(String city, String skill);
}