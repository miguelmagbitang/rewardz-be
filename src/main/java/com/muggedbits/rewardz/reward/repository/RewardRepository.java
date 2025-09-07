package com.muggedbits.rewardz.reward.repository;

import com.muggedbits.rewardz.reward.model.Reward;
import com.muggedbits.rewardz.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findByCampaignId(Long campaignId);

}
