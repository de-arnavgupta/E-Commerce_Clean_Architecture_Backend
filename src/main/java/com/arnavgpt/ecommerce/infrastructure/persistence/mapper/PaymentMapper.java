package com.arnavgpt.ecommerce.infrastructure.persistence.mapper;

import com.arnavgpt.ecommerce.domain.entity.Payment;
import com.arnavgpt.ecommerce.domain.entity.PaymentStatus;
import com.arnavgpt.ecommerce.infrastructure.persistence.document.PaymentDocument;

public class PaymentMapper {

    public static Payment toDomain(PaymentDocument document) {
        if (document == null) {
            return null;
        }

        return new Payment(
                document.getId(),
                document.getOrderId(),
                document.getAmount(),
                PaymentStatus.valueOf(document.getStatus()),
                document.getPaymentId(),
                document.getCreatedAt()
        );
    }

    public static PaymentDocument toDocument(Payment payment) {
        if (payment == null) {
            return null;
        }

        return new PaymentDocument(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus().name(),
                payment.getPaymentId(),
                payment.getCreatedAt()
        );
    }
}