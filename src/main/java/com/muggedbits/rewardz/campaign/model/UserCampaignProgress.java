package com.muggedbits.rewardz.campaign.model;

import com.muggedbits.rewardz.shared.BaseEntity;
import com.muggedbits.rewardz.shared.CampaignType;
import com.muggedbits.rewardz.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_campaign_progress")
@Setter
@Getter
public class UserCampaignProgress extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(nullable = false)
    private Integer progressValue = 0;

    @Column(nullable = false)
    private Boolean rewardEarned = false;
}
