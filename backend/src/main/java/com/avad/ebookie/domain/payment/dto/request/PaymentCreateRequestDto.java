package com.avad.ebookie.domain.payment.dto.request;

import com.avad.ebookie.domain.payment.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateRequestDto {
    private String paymentId; // null
    private Long orderId;
    private Double paidPrice;
    private PaymentStatus paymentStatus;
}
