package com.arnavgpt.ecommerce.domain.repository;

import com.arnavgpt.ecommerce.domain.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(String id);

    Optional<Payment> findByOrderId(String orderId);

    Optional<Payment> findByPaymentId(String paymentId);
}