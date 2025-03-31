package com.tenantservice.service;

import com.tenantservice.config.JwtUtil;
import com.tenantservice.dto.*;
import com.tenantservice.model.*;
import com.tenantservice.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ✅ Register tenant and return basic info
    public TenantResponse register(TenantRegisterRequest request) {
        Tenant tenant = Tenant.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(Role.TENANT)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        tenant = tenantRepository.save(tenant);
        return mapToResponse(tenant);
    }

    // ✅ Login and return JWT token in AuthResponse
    public AuthResponse login(TenantLoginRequest request) {
        Tenant tenant = tenantRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), tenant.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(tenant.getEmail());
        return new AuthResponse(token);
    }

    // ✅ Get tenant profile by ID
    public TenantResponse getProfile(Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        return mapToResponse(tenant);
    }

    // ✅ Update tenant profile by ID
    public TenantResponse updateProfile(Long tenantId, TenantProfileUpdateRequest request) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        tenant.setFullName(request.getFullName());
        tenant.setPhone(request.getPhone());
        tenant.setAddress(request.getAddress());

        tenant = tenantRepository.save(tenant);
        return mapToResponse(tenant);
    }

    // ✅ Converts entity to response DTO
    private TenantResponse mapToResponse(Tenant tenant) {
        return TenantResponse.builder()
                .id(tenant.getId())
                .fullName(tenant.getFullName())
                .email(tenant.getEmail())
                .phone(tenant.getPhone())
                .address(tenant.getAddress())
                .role(tenant.getRole())
                .build();
    }
}
