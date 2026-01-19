package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.domain.entity.Payment;
import com.arnavgpt.ecommerce.domain.repository.PaymentRepository;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.PaymentDocument;
import com.arnavgpt.ecommerce.infrastructure.persistence.mapper.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoPaymentRepository implements PaymentRepository {

    private final SpringDataPaymentRepository springDataRepository;

    public MongoPaymentRepository(SpringDataPaymentRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentDocument document = PaymentMapper.toDocument(payment);
        PaymentDocument savedDocument = springDataRepository.save(document);
        return PaymentMapper.toDomain(savedDocument);
    }

    @Override
    public Optional<Payment> findById(String id) {
        return springDataRepository.findById(id)
                .map(PaymentMapper::toDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(String orderId) {
        return springDataRepository.findByOrderId(orderId)
                .map(PaymentMapper::toDomain);
    }

    @Override
    public Optional<Payment> findByPaymentId(String paymentId) {
        return springDataRepository.findByPaymentId(paymentId)
                .map(PaymentMapper::toDomain);
    }
}