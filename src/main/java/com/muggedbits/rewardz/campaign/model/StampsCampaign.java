package com.muggedbits.rewardz.campaign.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "campaigns_stamps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StampsCampaign extends Campaign {

    @Column(nullable = false)
    private int visitsRequired;

    @Column
    private String rewardDescription;
}
