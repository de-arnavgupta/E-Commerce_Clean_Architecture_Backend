package com.arnavgpt.ecommerce.application.dto;

public record CreateProductRequest(
        String name,
        String description,
        Double price,
        Integer stock
) {
}