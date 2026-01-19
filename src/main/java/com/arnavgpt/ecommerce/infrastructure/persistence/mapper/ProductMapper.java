package com.arnavgpt.ecommerce.infrastructure.persistence.mapper;

import com.arnavgpt.ecommerce.domain.entity.Product;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.ProductDocument;

public class ProductMapper {

    public static Product toDomain(ProductDocument document) {
        if (document == null) {
            return null;
        }

        return new Product(
                document.getId(),
                document.getName(),
                document.getDescription(),
                document.getPrice(),
                document.getStock()
        );
    }

    public static ProductDocument toDocument(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDocument(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}