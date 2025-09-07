package com.muggedbits.rewardz.reward.model;

public record RewardDto(
        Long campaignId,
        String name,
        String description,
        Integer requiredPoints,
        Integer requiredStamps
) {
}
