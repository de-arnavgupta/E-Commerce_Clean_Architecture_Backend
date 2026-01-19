package com.arnavgpt.ecommerce.application.service;

import com.arnavgpt.ecommerce.application.dto.AddToCartRequest;
import com.arnavgpt.ecommerce.application.dto.CartItemResponse;
import com.arnavgpt.ecommerce.application.dto.ProductResponse;
import com.arnavgpt.ecommerce.domain.entity.CartItem;
import com.arnavgpt.ecommerce.domain.entity.Product;
import com.arnavgpt.ecommerce.domain.repository.CartItemRepository;
import com.arnavgpt.ecommerce.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public CartItemResponse addToCart(AddToCartRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + request.productId()));

        if (!product.isInStock(request.quantity())) {
            throw new IllegalStateException("Insufficient stock for product: " + product.getName());
        }

        Optional<CartItem> existingItem = cartItemRepository
                .findByUserIdAndProductId(request.userId(), request.productId());

        CartItem cartItem;
        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.increaseQuantity(request.quantity());
        } else {
            cartItem = new CartItem(
                    null,
                    request.userId(),
                    request.productId(),
                    request.quantity()
            );
        }

        CartItem savedItem = cartItemRepository.save(cartItem);
        return toResponse(savedItem, product);
    }

    public List<CartItemResponse> getCartByUserId(String userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        return cartItems.stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProductId())
                            .orElse(null);
                    return toResponse(item, product);
                })
                .collect(Collectors.toList());
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    private CartItemResponse toResponse(CartItem cartItem, Product product) {
        ProductResponse productResponse = null;
        if (product != null) {
            productResponse = new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            );
        }

        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getProductId(),
                cartItem.getQuantity(),
                productResponse
        );
    }
}