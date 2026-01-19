package com.arnavgpt.ecommerce.infrastructure.persistence.repository;

import com.arnavgpt.ecommerce.infrastructure.persistence.document.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataPaymentRepository extends MongoRepository<PaymentDocument, String> {

    Optional<PaymentDocument> findByOrderId(String orderId);

    Optional<PaymentDocument> findByPaymentId(String paymentId);
}