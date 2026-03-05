package com.rozgaarsetu.rozgaarsetu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "ratings",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_rating_deal_from", columnNames = {"deal_id", "from_user_id"})
        }
)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column(nullable = false)
    private int stars; // 1 to 5

    @Column(length = 300)
    private String comment;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}