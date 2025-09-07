package com.muggedbits.rewardz.reward.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.reward.model.Reward;
import com.muggedbits.rewardz.reward.model.RewardDto;
import com.muggedbits.rewardz.reward.repository.RewardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;
    private final EntityManager entityManager;

    public Reward create(RewardDto rewardDto) {
        Reward reward = new Reward();
        reward.setCampaign(entityManager.getReference(Campaign.class, rewardDto.campaignId()));
        reward.setName(rewardDto.name());
        reward.setDescription(rewardDto.description());
        reward.setRequiredPoints(rewardDto.requiredPoints());
        reward.setRequiredStamps(rewardDto.requiredStamps());
        return rewardRepository.save(reward);
    }

    public List<Reward> getByCampaign(Long campaignId) {
        return rewardRepository.findByCampaignId(campaignId);
    }

    public Optional<Reward> get(Long rewardId) {
        return rewardRepository.findById(rewardId);
    }

    public Reward update(Long rewardId, RewardDto updatedReward) {
        return rewardRepository.findById(rewardId)
                .map(reward -> {
                    reward.setName(updatedReward.name());
                    reward.setDescription(updatedReward.description());
                    reward.setRequiredPoints(updatedReward.requiredPoints());
                    reward.setRequiredStamps(updatedReward.requiredStamps());
                    return rewardRepository.save(reward);
                })
                .orElseThrow(() -> new RuntimeException("Reward not found"));
    }

    public void delete(Long rewardId) {
        rewardRepository.deleteById(rewardId);
    }
}
