package com.arnavgpt.ecommerce.application.service;

import com.arnavgpt.ecommerce.application.dto.CreateOrderRequest;
import com.arnavgpt.ecommerce.application.dto.OrderItemResponse;
import com.arnavgpt.ecommerce.application.dto.OrderResponse;
import com.arnavgpt.ecommerce.application.dto.PaymentResponse;
import com.arnavgpt.ecommerce.domain.entity.CartItem;
import com.arnavgpt.ecommerce.domain.entity.Order;
import com.arnavgpt.ecommerce.domain.entity.OrderItem;
import com.arnavgpt.ecommerce.domain.entity.OrderStatus;
import com.arnavgpt.ecommerce.domain.entity.Payment;
import com.arnavgpt.ecommerce.domain.entity.Product;
import com.arnavgpt.ecommerce.domain.repository.CartItemRepository;
import com.arnavgpt.ecommerce.domain.repository.OrderRepository;
import com.arnavgpt.ecommerce.domain.repository.PaymentRepository;
import com.arnavgpt.ecommerce.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    public OrderService(
            OrderRepository orderRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            PaymentRepository paymentRepository
    ) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(request.userId());

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + cartItem.getProductId()));

            if (!product.isInStock(cartItem.getQuantity())) {
                throw new IllegalStateException("Insufficient stock for product: " + product.getName());
            }

            OrderItem orderItem = new OrderItem(
                    UUID.randomUUID().toString(),
                    null,
                    product.getId(),
                    cartItem.getQuantity(),
                    product.getPrice()
            );

            orderItems.add(orderItem);
            totalAmount += orderItem.getSubtotal();

            product.decreaseStock(cartItem.getQuantity());
            productRepository.save(product);
        }

        Order order = new Order(
                null,
                request.userId(),
                totalAmount,
                OrderStatus.CREATED,
                Instant.now(),
                orderItems
        );

        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteByUserId(request.userId());

        return toResponse(savedOrder, null);
    }

    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        Payment payment = paymentRepository.findByOrderId(orderId).orElse(null);

        return toResponse(order, payment);
    }

    public List<OrderResponse> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(order -> {
                    Payment payment = paymentRepository.findByOrderId(order.getId()).orElse(null);
                    return toResponse(order, payment);
                })
                .collect(Collectors.toList());
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        order.setStatus(status);
        orderRepository.save(order);
    }

    private OrderResponse toResponse(Order order, Payment payment) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getSubtotal()
                ))
                .collect(Collectors.toList());

        PaymentResponse paymentResponse = null;
        if (payment != null) {
            paymentResponse = new PaymentResponse(
                    payment.getId(),
                    payment.getOrderId(),
                    payment.getAmount(),
                    payment.getStatus().name(),
                    payment.getPaymentId(),
                    payment.getCreatedAt()
            );
        }

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount(),
                order.getStatus().name(),
                order.getCreatedAt(),
                itemResponses,
                paymentResponse
        );
    }
}