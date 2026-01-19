package com.arnavgpt.ecommerce.application.dto;

public record UserResponse(
        String id,
        String username,
        String email,
        String role
) {
}