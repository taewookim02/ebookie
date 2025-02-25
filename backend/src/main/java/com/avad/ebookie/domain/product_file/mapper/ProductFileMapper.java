package com.avad.ebookie.domain.product_file.mapper;

import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import com.avad.ebookie.domain.product_file.entity.ProductFile;
import org.springframework.stereotype.Service;

@Service
public class ProductFileMapper {

    public ProductFileResponseDto toDto(ProductFile entity) {
        return ProductFileResponseDto.builder()
                .id(entity.getId())
                .productId(entity.getParentProduct().getId())
                .fileName(entity.getFileName())
                .originalName(entity.getOriginalName())
                .build();
    }
}
