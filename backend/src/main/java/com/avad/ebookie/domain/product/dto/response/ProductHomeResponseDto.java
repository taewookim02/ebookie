package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.category.dto.response.CategoryProductsResponseDto;
import com.avad.ebookie.domain.category.dto.response.CategoryResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ProductHomeResponseDto {
    private List<ProductTopFiveResponseDto> topFiveSellingProducts;
    private List<CategoryProductsResponseDto> categoryProducts;
//    private Map<Long, CategoryProductsResponseDto> categoryProducts;
//    private Map<String, List<ProductRelatedResponseDto>> categoryProducts;

}
