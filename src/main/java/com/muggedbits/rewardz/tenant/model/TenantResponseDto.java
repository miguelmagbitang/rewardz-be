package com.muggedbits.rewardz.tenant.model;

import com.muggedbits.rewardz.shared.PlanType;

import java.util.UUID;

public class TenantResponseDto {

    private UUID tenantId;
    private String name;
    private PlanType planType;
    private String contactEmail;
    private String contactPhone;
}
