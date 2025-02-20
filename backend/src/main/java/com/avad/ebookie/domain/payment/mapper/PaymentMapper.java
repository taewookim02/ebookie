package com.avad.ebookie.domain.payment.mapper;

import com.avad.ebookie.domain.payment.dto.response.PaymentResponseDto;
import com.avad.ebookie.domain.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public PaymentResponseDto toDto(Payment entity) {
        return PaymentResponseDto.builder()
                .orderId(entity.getOrder().getId())
                .paymentId(entity.getId())
                .build();
    }
}
