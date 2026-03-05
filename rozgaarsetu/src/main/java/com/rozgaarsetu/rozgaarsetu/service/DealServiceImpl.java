package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.CompleteDealRequest;
import com.rozgaarsetu.rozgaarsetu.dto.DealResponse;
import com.rozgaarsetu.rozgaarsetu.entity.*;
import com.rozgaarsetu.rozgaarsetu.repository.DealRepository;
import com.rozgaarsetu.rozgaarsetu.repository.WageRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final WageRateRepository wageRateRepository;

    @Override
    public DealResponse complete(CompleteDealRequest request) {

        Deal deal = dealRepository.findById(request.getDealId())
                .orElseThrow(() -> new RuntimeException("Deal not found"));

        Job job = deal.getJob();

        if (!job.getEmployer().getId().equals(request.getEmployerId())) {
            throw new RuntimeException("You are not the owner of this deal");
        }

        if (deal.getStatus() != DealStatus.LOCKED) {
            throw new RuntimeException("Deal is not in LOCKED state");
        }

        WageRate rate = wageRateRepository
                .findByCityIgnoreCaseAndSkillIgnoreCase(job.getCity(), job.getRequiredSkill())
                .orElseThrow(() -> new RuntimeException("Wage rate not found for this city & skill"));

        int minAmount = request.getFinalDays() * rate.getPerDayRate()
                + request.getOvertimeHours() * rate.getOvertimePerHourRate();

        if (request.getPaidAmount() < minAmount) {
            throw new RuntimeException("Paid amount is less than minimum payable amount: " + minAmount);
        }

        deal.setFinalDays(request.getFinalDays());
        deal.setOvertimeHours(request.getOvertimeHours());
        deal.setMinPayableAmount(minAmount);
        deal.setPaidAmount(request.getPaidAmount());
        deal.setStatus(DealStatus.COMPLETED);

        Deal saved = dealRepository.save(deal);

        return DealResponse.builder()
                .id(saved.getId())
                .jobId(saved.getJob().getId())
                .labourId(saved.getLabour().getId())
                .labourName(saved.getLabour().getFullName())
                .status(saved.getStatus())
                .build();
    }
}