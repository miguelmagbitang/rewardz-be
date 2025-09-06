package com.muggedbits.rewardz.campaign.controller;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.CampaignRequestDto;
import com.muggedbits.rewardz.campaign.service.CampaignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
@Slf4j
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<Campaign>> getAllCampaigns(@PathVariable UUID tenantId) {
        List<Campaign> campaigns = campaignService.getAllCampaignsForTenantId(tenantId);
        log.info(campaigns.toString());
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody CampaignRequestDto campaignRequestDto) {
        Campaign createdCampaign = campaignService.createCampaign(campaignRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCampaign);
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long campaignId) {
        Optional<Campaign> campaign = campaignService.getCampaignById(campaignId);
        return campaign.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
