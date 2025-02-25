package com.avad.ebookie.domain.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class ProductSearchResponseDto {
    private Long id;
    private String name;
    private String authorNames;
    private String category;
}
