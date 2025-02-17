package com.avad.ebookie.domain.liked_product.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikedProductResponseDto {
    private Long id;
    private String name;
    private String authorNames;
    private String thumbnail;
}
