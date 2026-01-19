package com.arnavgpt.ecommerce.infrastructure.persistence.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "orders")
public class OrderDocument {

    @Id
    private String id;
    private String userId;
    private Double totalAmount;
    private String status;
    private Instant createdAt;
    private List<OrderItemDocument> items;

    public OrderDocument() {
    }

    public OrderDocument(String id, String userId, Double totalAmount, String status, Instant createdAt, List<OrderItemDocument> items) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
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

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemDocument> getItems() {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setItems(List<OrderItemDocument> items) {
        this.items = items;
    }
}