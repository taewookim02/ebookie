package com.avad.ebookie.domain.product.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductLibraryItemResponseDto {
    private Long productId;
    private String title;
    private String thumbnailUrl;
    private List<ProductFileResponseDto> files;
}
