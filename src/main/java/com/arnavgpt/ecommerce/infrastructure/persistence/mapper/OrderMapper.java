package com.arnavgpt.ecommerce.infrastructure.persistence.mapper;

import com.arnavgpt.ecommerce.domain.entity.Order;
import com.arnavgpt.ecommerce.domain.entity.OrderItem;
import com.arnavgpt.ecommerce.domain.entity.OrderStatus;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.OrderDocument;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.OrderItemDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toDomain(OrderDocument document) {
        if (document == null) {
            return null;
        }

        List<OrderItem> items = new ArrayList<>();
        if (document.getItems() != null) {
            items = document.getItems().stream()
                    .map(itemDoc -> OrderItemMapper.toDomain(itemDoc, document.getId()))
                    .collect(Collectors.toList());
        }

        return new Order(
                document.getId(),
                document.getUserId(),
                document.getTotalAmount(),
                OrderStatus.valueOf(document.getStatus()),
                document.getCreatedAt(),
                items
        );
    }

    public static OrderDocument toDocument(Order order) {
        if (order == null) {
            return null;
        }

        List<OrderItemDocument> itemDocs = new ArrayList<>();
        if (order.getItems() != null) {
            itemDocs = order.getItems().stream()
                    .map(OrderItemMapper::toDocument)
                    .collect(Collectors.toList());
        }

        return new OrderDocument(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount(),
                order.getStatus().name(),
                order.getCreatedAt(),
                itemDocs
        );
    }
}