package com.avad.ebookie.domain.order_detail.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDetailResponseDto {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double originalPrice;
    private Double discountRatePercentage;
    private String thumbnail;
}
