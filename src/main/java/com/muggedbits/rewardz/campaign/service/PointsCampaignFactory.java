package com.muggedbits.rewardz.campaign.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.CampaignRequestDto;
import com.muggedbits.rewardz.campaign.model.PointsCampaign;
import com.muggedbits.rewardz.shared.CampaignType;
import org.springframework.stereotype.Service;

@Service
public class PointsCampaignFactory implements CampaignFactory {

    @Override
    public CampaignType getType() {
        return CampaignType.REWARD_POINTS;
    }

    @Override
    public Campaign create(CampaignRequestDto dto) {
        Campaign campaign = PointsCampaign.builder()
                .pointsPerPurchase(dto.pointsPerPurchase())
                .redeemThreshold(dto.redeemThreshold())
                .build();
        campaign.setName(dto.name());
        campaign.setDescription(dto.description());
        campaign.setTenantId(dto.tenantId());
        return campaign;
    }
}
