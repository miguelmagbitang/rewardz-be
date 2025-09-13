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
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final PointsCampaignRepository pointsCampaignRepository;
    private final StampsCampaignRepository stampsCampaignRepository;
    private final List<CampaignFactory> factories;

    /**
     * Get all campaigns of the tenant id
     * @param tenantId id of tenant
     * @return List of campaigns of this tenant
     */
    public List<Campaign> getAllCampaignsForTenantId(UUID tenantId) {
        return campaignRepository.findAllByTenantId(tenantId);
    }

    /**
     * Create campaign
     * @param campaignRequestDto campaign request DTO
     * @return Campaign created
     */
    public Campaign createCampaign(CampaignRequestDto campaignRequestDto) {
        return factories.stream()
                .filter(f -> f.getType().equals(campaignRequestDto.campaignType()))
                .findFirst()
                .map(f -> f.create(campaignRequestDto))
                .map(campaignRepository::save)
                .orElseThrow(() -> new IllegalStateException("Invalid campaign type"));
    }

    public void delete(Long campaignId) {
        campaignRepository.deleteById(campaignId);
    }

    /**
     * Activate or deactivate the campaign
     * @param campaignId
     * @param activate true if to activate, false if to deactivate
     * @return true if successful, false otherwise
     */
    public boolean activateOrDeactivate(Long campaignId, boolean activate) {
        return campaignRepository.findById(campaignId)
                .map(c -> setActiveField(c, activate))
                .orElse(false);
    }

    private boolean setActiveField(Campaign campaign, boolean activate) {
        campaign.setActive(activate);
        campaignRepository.save(campaign);
        return true;
    }

    /**
     * Get campaign by id
     * @param id
     * @return Optional campaign
     */
    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }


}
