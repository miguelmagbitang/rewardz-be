package com.muggedbits.rewardz.campaign.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.CampaignRequestDto;
import com.muggedbits.rewardz.campaign.model.PointsCampaign;
import com.muggedbits.rewardz.campaign.model.StampsCampaign;
import com.muggedbits.rewardz.shared.CampaignType;
import org.springframework.stereotype.Service;

@Service
public class StampCampaignFactory implements CampaignFactory {

    @Override
    public CampaignType getType() {
        return CampaignType.STAMP_CARD;
    }

    @Override
    public Campaign create(CampaignRequestDto dto) {
        Campaign campaign = StampsCampaign.builder()
                .limitCount(dto.stampLimit())
                .build();
        campaign.setName(dto.name());
        campaign.setDescription(dto.description());
        campaign.setTenantId(dto.tenantId());
        return campaign;
    }
}
