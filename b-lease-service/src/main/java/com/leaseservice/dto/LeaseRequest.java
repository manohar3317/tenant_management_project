package com.leaseservice.dto;

import com.leaseservice.model.LeaseStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaseRequest {
    private Long tenantId;
    private Long propertyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaseStatus status;
}
