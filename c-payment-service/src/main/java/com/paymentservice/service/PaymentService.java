package com.paymentservice.service;

import com.paymentservice.dto.PaymentRequest;
import com.paymentservice.dto.PaymentResponse;
import com.paymentservice.model.Payment;
import com.paymentservice.model.PaymentMethod;
import com.paymentservice.model.PaymentStatus;
import com.paymentservice.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // ✅ Simulated payment logic (no gateway)
    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentStatus status = PaymentStatus.SUCCESS; // Simulated outcome

        Payment payment = Payment.builder()
                .tenantId(request.getTenantId())
                .amount(request.getAmount())
                .method(request.getMethod())
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();

        payment = paymentRepository.save(payment);

        return buildResponse(payment);
    }

    // ✅ Real Stripe payment processing
    public PaymentResponse processStripePayment(PaymentRequest request) throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (request.getAmount().doubleValue() * 100)) // Stripe expects amount in cents
                .setCurrency("usd")
                .setSource("tok_visa") // Use test token or dynamic source in real case
                .setDescription("Payment from tenant " + request.getTenantId())
                .build();

        Charge charge = Charge.create(params);

        PaymentStatus status = charge.getPaid() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;

        Payment payment = Payment.builder()
                .tenantId(request.getTenantId())
                .amount(request.getAmount())
                .method(PaymentMethod.STRIPE)
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();

        payment = paymentRepository.save(payment);
        return buildResponse(payment);
    }

    // ✅ Common method to build response
    private PaymentResponse buildResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .tenantId(payment.getTenantId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .timestamp(payment.getTimestamp())
                .build();
    }
}
