package com.avad.ebookie.domain.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class ProductListResponseDto {
    private List<ProductRelatedResponseDto> products;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}
