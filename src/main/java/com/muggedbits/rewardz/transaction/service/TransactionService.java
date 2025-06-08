package com.muggedbits.rewardz.transaction.service;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.campaign.service.CampaignService;
import com.muggedbits.rewardz.security.service.CustomUserDetailsService;
import com.muggedbits.rewardz.transaction.model.Transaction;
import com.muggedbits.rewardz.transaction.model.TransactionRequestDto;
import com.muggedbits.rewardz.transaction.repository.TransactionRepository;
import com.muggedbits.rewardz.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CampaignService campaignService;
    private final CustomUserDetailsService userDetailsService;

    public Transaction create(TransactionRequestDto transactionRequestDto) {
        Campaign campaign = campaignService.getCampaignById(transactionRequestDto.getCampaignId())
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found with ID: " + transactionRequestDto.getCampaignId()));

        User user = userDetailsService.getUserById(transactionRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + transactionRequestDto.getUserId()));

        Transaction transaction = Transaction.builder()
                .campaign(campaign)
                .user(user)
                .amountSpent(transactionRequestDto.getAmountSpent())
                .transactionDate(transactionRequestDto.getTransactionDate())
                .notes(transactionRequestDto.getNotes())
                .tenantId(transactionRequestDto.getTenantId())
                .build();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllByTenant(UUID tenantId) {
        return transactionRepository.findByTenantId(tenantId);
    }

    public List<Transaction> getAllByCampaign(Long campaignId) {
        return transactionRepository.findByCampaignId(campaignId);
    }
}
