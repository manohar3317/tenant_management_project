package com.tenantservice.dto;

import lombok.Data;

@Data
public class TenantRegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
}
