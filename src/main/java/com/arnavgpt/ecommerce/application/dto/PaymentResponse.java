package com.arnavgpt.ecommerce.application.dto;

import java.time.Instant;

public record PaymentResponse(
        String id,
        String orderId,
        Double amount,
        String status,
        String paymentId,
        Instant createdAt
) {
}