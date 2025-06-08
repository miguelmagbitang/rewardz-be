package com.muggedbits.rewardz.transaction.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {
    private Long id;
    private Long campaignId;
    private Long userId;
    private int amountSpent;
    private String transactionDate; // Use String for date to avoid serialization issues
    private String notes;
    private String tenantId; // Use String for UUID to avoid serialization issues
}
