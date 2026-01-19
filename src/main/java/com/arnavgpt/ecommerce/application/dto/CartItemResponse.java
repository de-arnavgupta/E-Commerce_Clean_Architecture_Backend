package com.arnavgpt.ecommerce.application.dto;

public record CartItemResponse(
        String id,
        String productId,
        Integer quantity,
        ProductResponse product
) {
}