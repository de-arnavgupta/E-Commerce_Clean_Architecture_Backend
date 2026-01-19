package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.domain.entity.CartItem;
import com.arnavgpt.ecommerce.domain.repository.CartItemRepository;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.CartItemDocument;
import com.arnavgpt.ecommerce.infrastructure.persistence.mapper.CartItemMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoCartItemRepository implements CartItemRepository {

    private final SpringDataCartItemRepository springDataRepository;

    public MongoCartItemRepository(SpringDataCartItemRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        CartItemDocument document = CartItemMapper.toDocument(cartItem);
        CartItemDocument savedDocument = springDataRepository.save(document);
        return CartItemMapper.toDomain(savedDocument);
    }

    @Override
    public Optional<CartItem> findById(String id) {
        return springDataRepository.findById(id)
                .map(CartItemMapper::toDomain);
    }

    @Override
    public List<CartItem> findByUserId(String userId) {
        return springDataRepository.findByUserId(userId).stream()
                .map(CartItemMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartItem> findByUserIdAndProductId(String userId, String productId) {
        return springDataRepository.findByUserIdAndProductId(userId, productId)
                .map(CartItemMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        springDataRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(String userId) {
        springDataRepository.deleteByUserId(userId);
    }
}