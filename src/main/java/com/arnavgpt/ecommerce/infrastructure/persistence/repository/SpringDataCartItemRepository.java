package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.infrastructure.persistence.document.CartItemDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataCartItemRepository extends MongoRepository<CartItemDocument, String> {

    List<CartItemDocument> findByUserId(String userId);

    Optional<CartItemDocument> findByUserIdAndProductId(String userId, String productId);

    void deleteByUserId(String userId);
}