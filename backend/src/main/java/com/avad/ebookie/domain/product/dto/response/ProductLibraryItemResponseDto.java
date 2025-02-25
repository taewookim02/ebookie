package com.avad.ebookie.domain.product.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductLibraryItemResponseDto {
    private Long productId;
    private String title;
    private String thumbnailUrl;
}
