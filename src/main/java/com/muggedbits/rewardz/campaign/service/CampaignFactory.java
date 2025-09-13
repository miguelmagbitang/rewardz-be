package com.muggedbits.rewardz.campaign.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.CampaignRequestDto;
import com.muggedbits.rewardz.shared.CampaignType;

public interface CampaignFactory {

    CampaignType getType();

    Campaign create(CampaignRequestDto dto);
}
