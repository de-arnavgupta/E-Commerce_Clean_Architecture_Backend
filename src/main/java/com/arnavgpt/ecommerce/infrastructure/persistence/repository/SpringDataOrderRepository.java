package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.infrastructure.persistence.document.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataOrderRepository extends MongoRepository<OrderDocument, String> {

    List<OrderDocument> findByUserId(String userId);
}