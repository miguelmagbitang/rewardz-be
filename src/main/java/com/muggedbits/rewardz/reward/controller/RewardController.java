package com.muggedbits.rewardz.reward.controller;

import com.muggedbits.rewardz.reward.model.RedemptionRequestDto;
import com.muggedbits.rewardz.reward.model.Reward;
import com.muggedbits.rewardz.reward.model.RewardDto;
import com.muggedbits.rewardz.reward.model.RewardRedemption;
import com.muggedbits.rewardz.reward.service.RewardRedemptionService;
import com.muggedbits.rewardz.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;
    private final RewardRedemptionService redemptionService;

    @PostMapping
    public ResponseEntity<Reward> createReward(@RequestBody RewardDto reward) {
        return ResponseEntity.ok(rewardService.create(reward));
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<Reward>> getRewardsByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(rewardService.getByCampaign(campaignId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getReward(@PathVariable Long id) {
        return rewardService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(
            @PathVariable Long id,
            @RequestBody RewardDto updatedReward) {
        return ResponseEntity.ok(rewardService.update(id, updatedReward));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
        rewardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rewardId}/redeem")
    public ResponseEntity<Boolean> redeemReward(@PathVariable Long rewardId,
                                                @RequestBody RedemptionRequestDto redemptiontionDto) {
        var result = redemptionService.redeem(redemptiontionDto.userId(), rewardId);
        return ResponseEntity.ok(result);
    }
}
