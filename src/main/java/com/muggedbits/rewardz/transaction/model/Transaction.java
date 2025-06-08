package com.muggedbits.rewardz.transaction.model;

import com.muggedbits.rewardz.campaign.model.Campaign;
import com.muggedbits.rewardz.shared.BaseEntity;
import com.muggedbits.rewardz.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int amountSpent;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column
    private String notes;

    @Column
    private UUID tenantId;
}
