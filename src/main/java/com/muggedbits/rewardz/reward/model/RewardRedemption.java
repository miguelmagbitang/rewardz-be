package com.muggedbits.rewardz.reward.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muggedbits.rewardz.shared.BaseEntity;
import com.muggedbits.rewardz.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reward_redemptions")
@Setter
@Getter
public class RewardRedemption extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id", nullable = false)
    @JsonIgnore
    private Reward reward;

    @Column(nullable = false)
    private LocalDateTime redeemedAt;

    @Column(nullable = false)
    private String status;

    @Column
    private String notes;
}
