package com.muggedbits.rewardz.reward.service;

import com.muggedbits.rewardz.campaign.repository.UserCampaignProgressRepository;
import com.muggedbits.rewardz.reward.model.RewardRedemption;
import com.muggedbits.rewardz.reward.repository.RewardRedemptionRepository;
import com.muggedbits.rewardz.reward.repository.RewardRepository;
import com.muggedbits.rewardz.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardRedemptionService {

    private final RewardRedemptionRepository rewardRedemptionRepository;
    private final RewardRepository rewardRepository;
    private final UserCampaignProgressRepository progressRepository;

    public List<RewardRedemption> getByUser(Long userId) {
        return rewardRedemptionRepository.findByUserId(userId);
    }

    @Transactional
    public boolean redeem(Long userId, Long rewardId) {
        var reward = rewardRepository.findById(rewardId).orElseThrow(() -> new RuntimeException("Reward not found."));
        var progress = progressRepository.findByUserIdAndCampaignId(userId, reward.getCampaign().getId())
                .orElseThrow(() -> new IllegalStateException("No progress for user in campaign"));

        if (reward.getRequiredPoints() != null) {
            if (progress.getProgressValue() < reward.getRequiredPoints()) {
                return false;
            }
            // decrease points
            progress.setProgressValue(progress.getProgressValue() - reward.getRequiredPoints());
        } else if (reward.getRequiredStamps() != null) {
            if (progress.getProgressValue() < reward.getRequiredStamps()) {
                return false;
            }
            // don't decrease stamps, do nothing
        }

        RewardRedemption redemption = new RewardRedemption();
        redemption.setUser(progress.getUser());
        redemption.setReward(reward);
        redemption.setRedeemedAt(LocalDateTime.now());
        redemption.setStatus("SUCCESS");
        progressRepository.save(progress);
        rewardRedemptionRepository.save(redemption);
        return true;
    }
}
