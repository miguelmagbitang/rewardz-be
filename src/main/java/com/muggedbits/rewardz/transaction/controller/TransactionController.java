package com.muggedbits.rewardz.transaction.controller;

import com.muggedbits.rewardz.transaction.model.Transaction;
import com.muggedbits.rewardz.transaction.model.TransactionRequestDto;
import com.muggedbits.rewardz.transaction.model.TransactionResponseDto;
import com.muggedbits.rewardz.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionService.create(transactionRequestDto));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<TransactionResponseDto>> getByTenant(@PathVariable UUID tenantId) {
        List<Transaction> transactions = transactionService.getAllByTenant(tenantId);
        List<TransactionResponseDto> response = transactions.stream()
                .map(transaction -> TransactionResponseDto.builder()
                        .id(transaction.getId())
                        .campaignId(transaction.getCampaign().getId())
                        .userId(transaction.getUser().getId())
                        .amountSpent(transaction.getAmountSpent())
                        .transactionDate(transaction.getTransactionDate().toString()) // Convert LocalDateTime to String
                        .notes(transaction.getNotes())
                        .tenantId(String.valueOf(transaction.getTenantId()))
                        .build())
                .toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<TransactionResponseDto>> getByCampaign(@PathVariable Long campaignId) {
        List<Transaction> transactions = transactionService.getAllByCampaign(campaignId);
        List<TransactionResponseDto> response = transactions.stream()
                .map(transaction -> TransactionResponseDto.builder()
                        .id(transaction.getId())
                        .campaignId(transaction.getCampaign().getId())
                        .userId(transaction.getUser().getId())
                        .amountSpent(transaction.getAmountSpent())
                        .transactionDate(transaction.getTransactionDate().toString()) // Convert LocalDateTime to String
                        .notes(transaction.getNotes())
                        .tenantId(String.valueOf(transaction.getTenantId()))
                        .build())
                .toList();
        return ResponseEntity.ok(response);
    }
}
