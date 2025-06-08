package com.muggedbits.rewardz.transaction.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionRequestDto {

    private Long campaignId;

    private Long userId;

    private int amountSpent;

    private LocalDateTime transactionDate;

    private String notes;

    private UUID tenantId;
}