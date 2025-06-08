package com.muggedbits.rewardz.campaign.repository;

import com.muggedbits.rewardz.campaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findAllByTenantId(UUID tenantId);
}
