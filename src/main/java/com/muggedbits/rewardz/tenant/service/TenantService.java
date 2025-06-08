package com.muggedbits.rewardz.tenant.service;

import com.muggedbits.rewardz.shared.PlanType;
import com.muggedbits.rewardz.tenant.model.Tenant;
import com.muggedbits.rewardz.tenant.model.TenantRequestDto;
import com.muggedbits.rewardz.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    public Tenant createTenant(TenantRequestDto tenantRequestDto) {
        Tenant tenant = Tenant.builder()
                .name(tenantRequestDto.getName())
                .planType(tenantRequestDto.getPlanType())
                .contactEmail(tenantRequestDto.getContactEmail())
                .contactPhone(tenantRequestDto.getContactPhone())
                .isActive(true) // Default to active
                .build();
        return tenantRepository.save(tenant);
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> getByTenantId(UUID tenantId) {
        return tenantRepository.findByTenantId(tenantId);
    }

    public void deleteTenant(UUID tenantId) {
        tenantRepository.deleteByTenantId(tenantId);
    }

}
