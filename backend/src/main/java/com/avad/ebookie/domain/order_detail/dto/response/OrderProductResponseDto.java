package com.avad.ebookie.domain.order_detail.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductResponseDto {
    private Long productId;
    private String productName;
//    private String thumbnailUrl;
}
