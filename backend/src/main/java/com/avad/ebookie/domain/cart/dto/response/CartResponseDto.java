package com.avad.ebookie.domain.cart.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartResponseDto {
    private Long id;
    private Long productId;
    private String name;
    private String authorNames;
    private String thumbnail;
}
