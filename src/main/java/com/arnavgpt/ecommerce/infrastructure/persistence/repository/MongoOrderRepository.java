package com.arnavgpt.ecommerce.infrastructure.persistence.repository;


import com.arnavgpt.ecommerce.domain.entity.Order;
import com.arnavgpt.ecommerce.domain.repository.OrderRepository;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.OrderDocument;
import com.arnavgpt.ecommerce.infrastructure.persistence.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoOrderRepository implements OrderRepository {

    private final SpringDataOrderRepository springDataRepository;

    public MongoOrderRepository(SpringDataOrderRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Order save(Order order) {
        OrderDocument document = OrderMapper.toDocument(order);
        OrderDocument savedDocument = springDataRepository.save(document);
        return OrderMapper.toDomain(savedDocument);
    }

    @Override
    public Optional<Order> findById(String id) {
        return springDataRepository.findById(id)
                .map(OrderMapper::toDomain);
    }

    @Override
    public List<Order> findByUserId(String userId) {
        return springDataRepository.findByUserId(userId).stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return springDataRepository.existsById(id);
    }
}