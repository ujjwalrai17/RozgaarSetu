package com.rozgaarsetu.rozgaarsetu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "wage_rates",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_city_skill", columnNames = {"city", "skill"})
        }
)
public class WageRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String skill; // Mason, Plumber, Electrician etc.

    @Column(nullable = false)
    private int perDayRate; // minimum per day

    @Column(nullable = false)
    private int overtimePerHourRate;

    @Column(nullable = false)
    private LocalDate effectiveFrom;
}