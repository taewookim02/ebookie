package com.avad.ebookie.domain.saved_product.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SavedProductResponseDto {
    private Long id;
    private Long productId;
    private String name;
    private String authorNames;
    private String thumbnail;
}
