package com.leaseservice.service;

import com.leaseservice.dto.LeaseRequest;
import com.leaseservice.dto.LeaseResponse;
import com.leaseservice.model.Lease;
import com.leaseservice.repository.LeaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaseService {

    private final LeaseRepository leaseRepository;

    public LeaseResponse createLease(LeaseRequest request) {
        Lease lease = Lease.builder()
                .tenantId(request.getTenantId())
                .propertyId(request.getPropertyId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus())
                .build();

        lease = leaseRepository.save(lease);
        return mapToResponse(lease);
    }

    public LeaseResponse updateLease(Long leaseId, LeaseRequest request) {
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        lease.setStartDate(request.getStartDate());
        lease.setEndDate(request.getEndDate());
        lease.setStatus(request.getStatus());

        lease = leaseRepository.save(lease);
        return mapToResponse(lease);
    }

    public LeaseResponse renewLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        lease.setStatus(com.leaseservice.model.LeaseStatus.RENEWED);
        lease = leaseRepository.save(lease);

        return mapToResponse(lease);
    }

    private LeaseResponse mapToResponse(Lease lease) {
        return LeaseResponse.builder()
                .id(lease.getId())
                .tenantId(lease.getTenantId())
                .propertyId(lease.getPropertyId())
                .startDate(lease.getStartDate())
                .endDate(lease.getEndDate())
                .status(lease.getStatus())
                .build();
    }
}
