package com.arnavgpt.ecommerce.infrastructure.persistence.mapper;

import com.arnavgpt.ecommerce.domain.entity.CartItem;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.CartItemDocument;

public class CartItemMapper {

    public static CartItem toDomain(CartItemDocument document) {
        if (document == null) {
            return null;
        }

        return new CartItem(
                document.getId(),
                document.getUserId(),
                document.getProductId(),
                document.getQuantity()
        );
    }

    public static CartItemDocument toDocument(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        return new CartItemDocument(
                cartItem.getId(),
                cartItem.getUserId(),
                cartItem.getProductId(),
                cartItem.getQuantity()
        );
    }
}