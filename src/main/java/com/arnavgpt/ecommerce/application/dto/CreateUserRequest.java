package com.arnavgpt.ecommerce.application.dto;

public record CreateUserRequest(
        String username,
        String email,
        String role
) {
}