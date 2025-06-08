package com.muggedbits.rewardz.campaign.controller;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.model.CampaignRequestDto;
import com.muggedbits.rewardz.campaign.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<Campaign>> getAllCampaigns(@PathVariable UUID tenantId) {
        List<Campaign> campaigns = campaignService.getAllCampaignsForTenantId(tenantId);
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody CampaignRequestDto campaignRequestDto) {
        Campaign createdCampaign = campaignService.createCampaign(campaignRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCampaign);
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Optional<Campaign> campaign = campaignService.getCampaignById(id);
        return campaign.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
