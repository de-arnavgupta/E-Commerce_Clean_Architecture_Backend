package com.arnavgpt.ecommerce.application.dto;

public record AddToCartRequest(
        String userId,
        String productId,
        Integer quantity
) {
}