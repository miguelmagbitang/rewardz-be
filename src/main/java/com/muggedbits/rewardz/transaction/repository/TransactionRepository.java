package com.muggedbits.rewardz.transaction.repository;

import com.muggedbits.rewardz.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTenantId(UUID tenantId);
    List<Transaction> findByCampaignId(Long campaignId);
}
