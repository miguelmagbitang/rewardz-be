package com.muggedbits.rewardz.campaign.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.PointsCampaign;
import com.muggedbits.rewardz.campaign.model.StampsCampaign;
import com.muggedbits.rewardz.campaign.model.UserCampaignProgress;
import com.muggedbits.rewardz.campaign.repository.CampaignRepository;
import com.muggedbits.rewardz.campaign.repository.UserCampaignProgressRepository;
import com.muggedbits.rewardz.user.model.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignProgressService {

    private final UserCampaignProgressRepository progressRepository;
    private final CampaignRepository campaignRepository;
    private final EntityManager entityManager;

    @Transactional
    public UserCampaignProgress incrementProgress(Long userId, Long campaignId, int delta) {
        var progress = progressRepository.findByUserIdAndCampaignId(userId, campaignId);
        UserCampaignProgress userCampaignProgress;
        if (progress.isEmpty()) {
            userCampaignProgress = new UserCampaignProgress();
            userCampaignProgress.setCampaign(entityManager.getReference(Campaign.class, campaignId));
            userCampaignProgress.setUser(entityManager.getReference(User.class, userId));
        } else {
            userCampaignProgress = progress.get();
        }
        userCampaignProgress.setProgressValue(userCampaignProgress.getProgressValue() + delta);
        return progressRepository.save(userCampaignProgress);
    }
}
