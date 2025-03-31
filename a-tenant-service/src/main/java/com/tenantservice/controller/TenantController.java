package com.tenantservice.controller;

import com.tenantservice.dto.*;
import com.tenantservice.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    // ✅ Registration returns basic tenant profile
    @PostMapping("/register")
    public ResponseEntity<TenantResponse> register(@RequestBody TenantRegisterRequest request) {
        return ResponseEntity.ok(tenantService.register(request));
    }

    // ✅ Login returns JWT token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody TenantLoginRequest request) {
        return ResponseEntity.ok(tenantService.login(request));
    }

    // ✅ Get tenant profile by ID
    @GetMapping("/profile/{id}")
    public ResponseEntity<TenantResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(tenantService.getProfile(id));
    }

    // ✅ Update tenant profile by ID
    @PutMapping("/profile/{id}")
    public ResponseEntity<TenantResponse> updateProfile(@PathVariable Long id,
                                                        @RequestBody TenantProfileUpdateRequest request) {
        return ResponseEntity.ok(tenantService.updateProfile(id, request));
    }
}
