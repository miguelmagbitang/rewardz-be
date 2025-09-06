package com.muggedbits.rewardz.campaign.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "campaigns_points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue("REWARD_POINTS")
public class PointsCampaign extends Campaign {

    @Column(nullable = false)
    private Integer pointsPerPurchase;

    @Column
    private Integer redeemThreshold;
}
