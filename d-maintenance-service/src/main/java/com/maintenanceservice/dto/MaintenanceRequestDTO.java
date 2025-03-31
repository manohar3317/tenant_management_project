package com.maintenanceservice.dto;

import lombok.Data;

@Data
public class MaintenanceRequestDTO {
    private Long tenantId;
    private String description;
}
