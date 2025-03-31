package com.maintenanceservice.dto;

import com.maintenanceservice.model.RequestStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MaintenanceResponseDTO {
    private Long id;
    private Long tenantId;
    private String description;
    private RequestStatus status;
    private LocalDateTime createdAt;
}
