package com.muggedbits.rewardz.tenant.model;

import com.muggedbits.rewardz.shared.BaseEntity;
import com.muggedbits.rewardz.shared.PlanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tenants")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Tenant extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String contactEmail;

    @Column
    private String contactPhone;

    @Column
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column
    private PlanType planType;

    @Column(nullable = false)
    private UUID tenantId;

    @PrePersist
    protected void prePersist() {
        tenantId = UUID.randomUUID();
    }

}
