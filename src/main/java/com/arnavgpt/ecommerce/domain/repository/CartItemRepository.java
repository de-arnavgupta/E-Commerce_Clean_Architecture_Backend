package com.arnavgpt.ecommerce.domain.repository;


import com.arnavgpt.ecommerce.domain.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findById(String id);

    List<CartItem> findByUserId(String userId);

    Optional<CartItem> findByUserIdAndProductId(String userId, String productId);

    void deleteById(String id);

    void deleteByUserId(String userId);
}