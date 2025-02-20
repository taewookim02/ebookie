package com.avad.ebookie.domain.payment.dto;

import com.avad.ebookie.domain.payment.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompleteResponseDto {
    private String paymentId;
    private PaymentStatus paymentStatus;

}
