package com.muggedbits.rewardz.campaign.repository;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.UserCampaignProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCampaignProgressRepository extends JpaRepository<UserCampaignProgress, Long> {

    Optional<UserCampaignProgress> findByUserIdAndCampaignId(Long userId, Long campaignId);

    List<UserCampaignProgress> findAllByCampaignId(Long campaignId);
}
