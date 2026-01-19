package com.arnavgpt.ecommerce.application.dto;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        String userId,
        Double totalAmount,
        String status,
        Instant createdAt,
        List<OrderItemResponse> items,
        PaymentResponse payment
) {
}