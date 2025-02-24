package com.avad.ebookie.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductListResponseDto {
    private List<ProductListItemResponseDto> products;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private String categoryName;
}
