package com.muggedbits.rewardz.reward.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.shared.BaseEntity;
import com.muggedbits.rewardz.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rewards")
@Setter
@Getter
public class Reward extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    @JsonIgnore
    private Campaign campaign;

    private String name;

    private String description;

    private Integer requiredPoints;

    private Integer requiredStamps;
}
