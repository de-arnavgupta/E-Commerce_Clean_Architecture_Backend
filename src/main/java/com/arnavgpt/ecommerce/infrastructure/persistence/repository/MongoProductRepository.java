package com.arnavgpt.ecommerce.infrastructure.persistence.repository;


import com.arnavgpt.ecommerce.domain.entity.Product;
import com.arnavgpt.ecommerce.domain.repository.ProductRepository;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.ProductDocument;
import com.arnavgpt.ecommerce.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoProductRepository implements ProductRepository {

    private final SpringDataProductRepository springDataRepository;

    public MongoProductRepository(SpringDataProductRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Product save(Product product) {
        ProductDocument document = ProductMapper.toDocument(product);
        ProductDocument savedDocument = springDataRepository.save(document);
        return ProductMapper.toDomain(savedDocument);
    }

    @Override
    public Optional<Product> findById(String id) {
        return springDataRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return springDataRepository.findAll().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        springDataRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return springDataRepository.existsById(id);
    }
}