package com.muggedbits.rewardz.campaign.model;

import com.muggedbits.rewardz.shared.BaseEntity;
import com.muggedbits.rewardz.shared.CampaignType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "campaigns")
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "campaign_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Campaign extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private boolean isActive;

    @Column
    private UUID tenantId;

}
