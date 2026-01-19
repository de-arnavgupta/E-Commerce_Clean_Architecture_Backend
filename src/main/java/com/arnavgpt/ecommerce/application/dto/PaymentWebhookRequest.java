package com.arnavgpt.ecommerce.application.dto;

public record PaymentWebhookRequest(
        String orderId,
        String paymentId,
        String status
) {
}