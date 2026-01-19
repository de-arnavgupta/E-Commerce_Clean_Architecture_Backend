package com.arnavgpt.ecommerce.application.dto;

public record ProductResponse(
        String id,
        String name,
        String description,
        Double price,
        Integer stock
) {
}