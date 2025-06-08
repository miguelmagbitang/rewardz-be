package com.muggedbits.rewardz.campaign.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.CampaignRequestDto;
import com.muggedbits.rewardz.campaign.model.PointsCampaign;
import com.muggedbits.rewardz.campaign.model.StampsCampaign;
import com.muggedbits.rewardz.campaign.repository.CampaignRepository;
import com.muggedbits.rewardz.campaign.repository.PointsCampaignRepository;
import com.muggedbits.rewardz.campaign.repository.StampsCampaignRepository;
import com.muggedbits.rewardz.shared.CampaignType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final PointsCampaignRepository pointsCampaignRepository;
    private final StampsCampaignRepository stampsCampaignRepository;

    public List<Campaign> getAllCampaignsForTenantId(UUID tenantId) {
        return campaignRepository.findAllByTenantId(tenantId);
    }

    public Campaign createCampaign(CampaignRequestDto campaignRequestDto) {
        Campaign campaign;
        if (campaignRequestDto.getCampaignType().equals(CampaignType.REWARD_POINTS)) {
            campaign = PointsCampaign.builder()
                    .pointsPerPurchase(campaignRequestDto.getPointsPerPurchase())
                    .redeemThreshold(campaignRequestDto.getRedeemThreshold())
                    .build();
        } else if (campaignRequestDto.getCampaignType().equals(CampaignType.STAMP_CARD)) {
            campaign = StampsCampaign.builder()
                    .visitsRequired(campaignRequestDto.getVisitsRequired())
                    .rewardDescription(campaignRequestDto.getRewardDescription())
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid campaign type: " + campaignRequestDto.getCampaignType());
        }
        campaign.setName(campaignRequestDto.getName());
        campaign.setDescription(campaignRequestDto.getDescription());
        campaign.setActive(true);
        campaign.setTenantId(campaignRequestDto.getTenantId());
        return campaignRepository.save(campaign);
    }

    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }


}
