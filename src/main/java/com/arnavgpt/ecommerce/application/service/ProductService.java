package com.arnavgpt.ecommerce.application.service;


import com.arnavgpt.ecommerce.application.dto.CreateProductRequest;
import com.arnavgpt.ecommerce.application.dto.ProductResponse;
import com.arnavgpt.ecommerce.domain.entity.Product;
import com.arnavgpt.ecommerce.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product(
                null,
                request.name(),
                request.description(),
                request.price(),
                request.stock()
        );

        Product savedProduct = productRepository.save(product);
        return toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        return toResponse(product);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}