package com.avad.ebookie.domain.payment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class PaymentWebhookRequestDto {
    private String webhookId;
    private String webhookTimestamp;
    private String webhookSignature;
}
