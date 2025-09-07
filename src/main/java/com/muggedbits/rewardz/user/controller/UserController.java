package com.muggedbits.rewardz.user.controller;

import com.muggedbits.rewardz.campaign.model.UserCampaignProgress;
import com.muggedbits.rewardz.campaign.service.CampaignProgressService;
import com.muggedbits.rewardz.reward.model.RewardRedemption;
import com.muggedbits.rewardz.reward.service.RewardRedemptionService;
import com.muggedbits.rewardz.user.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final RewardRedemptionService redemptionService;
    private final CampaignProgressService progressService;

    @GetMapping
    public String getUser() {
        return "hello";
    }

    @GetMapping("/{userId}/redemptions")
    public ResponseEntity<List<RewardRedemption>> getRedemptions(@PathVariable Long userId) {
        var redemptions = redemptionService.getByUser(userId);
        return ResponseEntity.ok(redemptions);
    }

    @GetMapping("/{userId}/progress")
    public ResponseEntity<UserCampaignProgress> getProgress(@PathVariable Long userId, @RequestParam Long campaignId) {
        var result = progressService.get(userId, campaignId);
        return ResponseEntity.ok(result);
    }

}
