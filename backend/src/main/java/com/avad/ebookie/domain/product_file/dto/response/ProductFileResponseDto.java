package com.avad.ebookie.domain.product_file.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductFileResponseDto {
    private Long id;
    private Long productId;
    private String fileName;
    private String originalName;
    private String fullPath;
}
