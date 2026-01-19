package com.arnavgpt.ecommerce.domain.entity;

import java.time.Instant;

public class Payment {

    private String id;
    private String orderId;
    private Double amount;
    private PaymentStatus status;
    private String paymentId;
    private Instant createdAt;

    public Payment() {
    }

    public Payment(String id, String orderId, Double amount, PaymentStatus status, String paymentId, Instant createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentId = paymentId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // Business methods
    public void markAsSuccess() {
        if (this.status != PaymentStatus.PENDING) {
            throw new IllegalStateException("Payment can only be marked success when PENDING");
        }
        this.status = PaymentStatus.SUCCESS;
    }

    public void markAsFailed() {
        if (this.status != PaymentStatus.PENDING) {
            throw new IllegalStateException("Payment can only be marked failed when PENDING");
        }
        this.status = PaymentStatus.FAILED;
    }

    public boolean isSuccess() {
        return this.status == PaymentStatus.SUCCESS;
    }

    public boolean isPending() {
        return this.status == PaymentStatus.PENDING;
    }
}