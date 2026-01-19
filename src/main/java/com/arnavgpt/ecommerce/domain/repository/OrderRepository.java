package com.arnavgpt.ecommerce.domain.repository;

import com.arnavgpt.ecommerce.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(String id);

    List<Order> findByUserId(String userId);

    boolean existsById(String id);
}