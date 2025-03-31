package com.tenantservice.dto;

import lombok.Data;

@Data
public class TenantProfileUpdateRequest {
    private String fullName;
    private String phone;
    private String address;
}
