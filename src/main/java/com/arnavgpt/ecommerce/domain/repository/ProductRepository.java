package com.arnavgpt.ecommerce.domain.repository;

import com.arnavgpt.ecommerce.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();

    void deleteById(String id);

    boolean existsById(String id);
}