package com.arnavgpt.ecommerce.application.service;

import com.arnavgpt.ecommerce.application.dto.CreatePaymentRequest;
import com.arnavgpt.ecommerce.application.dto.PaymentResponse;
import com.arnavgpt.ecommerce.application.dto.PaymentWebhookRequest;
import com.arnavgpt.ecommerce.domain.entity.Order;
import com.arnavgpt.ecommerce.domain.entity.Payment;
import com.arnavgpt.ecommerce.domain.entity.PaymentStatus;
import com.arnavgpt.ecommerce.domain.repository.OrderRepository;
import com.arnavgpt.ecommerce.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {
        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + request.orderId()));

        if (!order.canBePaid()) {
            throw new IllegalStateException("Order cannot be paid. Current status: " + order.getStatus());
        }

        if (!order.getTotalAmount().equals(request.amount())) {
            throw new IllegalArgumentException("Payment amount does not match order total");
        }

        Payment payment = new Payment(
                null,
                request.orderId(),
                request.amount(),
                PaymentStatus.PENDING,
                null,
                Instant.now()
        );

        Payment savedPayment = paymentRepository.save(payment);
        return toResponse(savedPayment);
    }

    public void processWebhook(PaymentWebhookRequest request) {
        Payment payment = paymentRepository.findByOrderId(request.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found for order: " + request.orderId()));

        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + request.orderId()));

        if ("SUCCESS".equalsIgnoreCase(request.status())) {
            payment.markAsSuccess();
            payment.setPaymentId(request.paymentId());
            order.markAsPaid();
        } else {
            payment.markAsFailed();
            order.markAsFailed();
        }

        paymentRepository.save(payment);
        orderRepository.save(order);
    }

    public PaymentResponse getPaymentByOrderId(String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found for order: " + orderId));
        return toResponse(payment);
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus().name(),
                payment.getPaymentId(),
                payment.getCreatedAt()
        );
    }
}