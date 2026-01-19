package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.infrastructure.persistence.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProductRepository extends MongoRepository<ProductDocument, String> {
}