package com.rozgaarsetu.rozgaarsetu.controller;

import com.rozgaarsetu.rozgaarsetu.dto.WageRateRequest;
import com.rozgaarsetu.rozgaarsetu.entity.WageRate;
import com.rozgaarsetu.rozgaarsetu.repository.WageRateRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/wage-rates")
@RequiredArgsConstructor
public class WageRateController {

    private final WageRateRepository wageRateRepository;

    @PostMapping
    public WageRate add(@Valid @RequestBody WageRateRequest req) {
        WageRate rate = WageRate.builder()
                .city(req.getCity())
                .skill(req.getSkill())
                .perDayRate(req.getPerDayRate())
                .overtimePerHourRate(req.getOvertimePerHourRate())
                .effectiveFrom(LocalDate.now())
                .build();

        return wageRateRepository.save(rate);
    }

    @GetMapping
    public WageRate get(@RequestParam String city, @RequestParam String skill) {
        return wageRateRepository.findByCityIgnoreCaseAndSkillIgnoreCase(city, skill)
                .orElseThrow(() -> new RuntimeException("Wage rate not found"));
    }
}