package com.muggedbits.rewardz.tenant.repository;

import com.muggedbits.rewardz.tenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findByTenantId(UUID tenantId);
    void deleteByTenantId(UUID tenantId);
}
