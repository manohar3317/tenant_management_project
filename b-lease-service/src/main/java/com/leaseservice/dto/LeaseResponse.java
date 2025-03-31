package com.leaseservice.dto;

import com.leaseservice.model.LeaseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LeaseResponse {
    private Long id;
    private Long tenantId;
    private Long propertyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaseStatus status;
}
