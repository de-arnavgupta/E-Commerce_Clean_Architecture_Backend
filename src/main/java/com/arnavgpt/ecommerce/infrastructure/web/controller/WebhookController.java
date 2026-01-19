package com.arnavgpt.ecommerce.infrastructure.web.controller;

import com.arnavgpt.ecommerce.application.dto.PaymentWebhookRequest;
import com.arnavgpt.ecommerce.application.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final PaymentService paymentService;

    public WebhookController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<Map<String, String>> handlePaymentWebhook(@RequestBody PaymentWebhookRequest request) {
        paymentService.processWebhook(request);
        return ResponseEntity.ok(Map.of("message", "Webhook processed successfully"));
    }
}