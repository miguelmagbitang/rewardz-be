package com.muggedbits.rewardz.campaign.model;

import com.muggedbits.rewardz.shared.CampaignType;
import lombok.Data;

import java.util.UUID;

@Data
public class CampaignRequestDto {
    private String name;
    private String description;
    private String startDate = null;
    private String endDate = null;
    private UUID tenantId;
    private CampaignType campaignType;

    private Integer pointsPerPurchase ;
    private Integer redeemThreshold;

    private Integer visitsRequired;
    private String rewardDescription;
}
