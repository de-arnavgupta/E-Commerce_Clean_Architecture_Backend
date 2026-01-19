package com.arnavgpt.ecommerce.application.dto;

public record CreatePaymentRequest(
        String orderId,
        Double amount
) {
}