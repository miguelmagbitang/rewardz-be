package com.muggedbits.rewardz.campaign.model;

import com.muggedbits.rewardz.shared.CampaignType;

import java.util.UUID;

public record CampaignRequestDto(
        String name,
        String description,
        String startDate,
        String endDate,
        UUID tenantId,
        CampaignType campaignType,
        Integer pointsPerPurchase,
        Integer redeemThreshold,
        Integer stampLimit
) {

}
