package com.avad.ebookie.domain.category.dto.response;

import com.avad.ebookie.domain.product.dto.response.ProductRelatedResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryProductsResponseDto {
    private Long id;
    private String name;
    private List<ProductRelatedResponseDto> products;
}
