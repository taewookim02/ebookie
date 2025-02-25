package com.avad.ebookie.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductLibraryListResponseDto {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private List<ProductLibraryItemResponseDto> libraryDtos;
}

