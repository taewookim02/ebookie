package com.avad.ebookie.domain.payment.controller;

import com.avad.ebookie.domain.payment.dto.PaymentCompleteResponseDto;
import com.avad.ebookie.domain.payment.dto.request.PaymentCompleteRequestDto;
import com.avad.ebookie.domain.payment.dto.request.PaymentCreateRequestDto;
import com.avad.ebookie.domain.payment.dto.response.PaymentResponseDto;
import com.avad.ebookie.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentCreateRequestDto requestDto) {
        PaymentResponseDto paymentResponseDto = paymentService.createPayment(requestDto);
        return ResponseEntity.ok(paymentResponseDto);
    }

    @PostMapping("/payments/complete")
    public ResponseEntity<PaymentCompleteResponseDto> completePayment(@RequestBody PaymentCompleteRequestDto requestDto) {
        PaymentCompleteResponseDto responseDto = paymentService.completePayment(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // TODO: 후순위
    @PostMapping("/payments/webhook")
    public ResponseEntity<?> webhook(@RequestBody String payload,
                                     @RequestHeader("webhook-id") String webhookId,
                                     @RequestHeader("webhook-signature") String webhookSignature,
                                     @RequestHeader("webhook-timestamp") String webhookTimestamp
    ) {
        System.out.println("payload = " + payload);
        System.out.println("webhookId = " + webhookId);
        System.out.println("webhookSignature = " + webhookSignature);
        System.out.println("webhookTimestamp = " + webhookTimestamp);

        return null;
    }
}

