package com.avad.ebookie.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductTopFiveResponseDto {
    private Long productId;
    private String name;
    private String thumbnail;
    private String authorNames;
}
