package com.muggedbits.rewardz.reward.repository;

import com.muggedbits.rewardz.reward.model.RewardRedemption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRedemptionRepository extends JpaRepository<RewardRedemption, Long> {

    List<RewardRedemption> findByUserId(Long userId);

    List<RewardRedemption> findByRewardId(Long rewardId);
}
