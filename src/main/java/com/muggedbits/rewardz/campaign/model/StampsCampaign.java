package com.muggedbits.rewardz.campaign.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
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
@DiscriminatorValue("STAMP_CARD")
public class StampsCampaign extends Campaign {

    @Column(nullable = false)
    private Integer limitCount;

}
