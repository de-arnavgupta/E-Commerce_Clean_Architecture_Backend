package com.arnavgpt.ecommerce.domain.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String id;
    private String userId;
    private Double totalAmount;
    private OrderStatus status;
    private Instant createdAt;
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(String id, String userId, Double totalAmount, OrderStatus status, Instant createdAt, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items != null ? items : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void markAsPaid() {
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order can only be paid when in CREATED status");
        }
        this.status = OrderStatus.PAID;
    }

    public void markAsFailed() {
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order can only be marked failed when in CREATED status");
        }
        this.status = OrderStatus.FAILED;
    }

    public void cancel() {
        if (this.status == OrderStatus.PAID) {
            throw new IllegalStateException("Cannot cancel a paid order");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public boolean isPaid() {
        return this.status == OrderStatus.PAID;
    }

    public boolean canBePaid() {
        return this.status == OrderStatus.CREATED;
    }
}