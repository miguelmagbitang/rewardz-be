package com.muggedbits.rewardz.campaign.model;

import jakarta.persistence.Column;
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
public class PointsCampaign extends Campaign {

    @Column(nullable = false)
    private int pointsPerPurchase;

    @Column
    private int redeemThreshold;
}
