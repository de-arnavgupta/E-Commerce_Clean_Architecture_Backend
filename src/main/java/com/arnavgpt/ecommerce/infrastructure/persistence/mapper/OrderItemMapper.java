package com.arnavgpt.ecommerce.infrastructure.persistence.mapper;

import com.arnavgpt.ecommerce.domain.entity.OrderItem;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.OrderItemDocument;

public class OrderItemMapper {

    public static OrderItem toDomain(OrderItemDocument document, String orderId) {
        if (document == null) {
            return null;
        }

        return new OrderItem(
                document.getId(),
                orderId,
                document.getProductId(),
                document.getQuantity(),
                document.getPrice()
        );
    }

    public static OrderItemDocument toDocument(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return new OrderItemDocument(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}