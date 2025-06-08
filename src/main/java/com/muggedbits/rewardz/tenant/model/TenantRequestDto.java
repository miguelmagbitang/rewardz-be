package com.muggedbits.rewardz.tenant.model;

import com.muggedbits.rewardz.shared.PlanType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantRequestDto {
    @NotBlank
    private String name;
    private String contactEmail;
    private String contactPhone;
    private PlanType planType;
}
