package com.arnavgpt.ecommerce.application.dto;

public record OrderItemResponse(
        String productId,
        Integer quantity,
        Double price,
        Double subtotal
) {
}