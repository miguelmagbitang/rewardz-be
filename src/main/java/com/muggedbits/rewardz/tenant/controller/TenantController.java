package com.muggedbits.rewardz.tenant.controller;

import com.muggedbits.rewardz.tenant.model.Tenant;
import com.muggedbits.rewardz.tenant.model.TenantRequestDto;
import com.muggedbits.rewardz.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody TenantRequestDto tenantRequestDto) {
        Tenant createdTenant = tenantService.createTenant(tenantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable UUID tenantId) {
        Optional<Tenant> tenant = tenantService.getByTenantId(tenantId);
        return tenant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
