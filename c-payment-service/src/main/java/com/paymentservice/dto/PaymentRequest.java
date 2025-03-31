package com.paymentservice.dto;

import com.paymentservice.model.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Long tenantId;
    private BigDecimal amount;
    private PaymentMethod method;
}
