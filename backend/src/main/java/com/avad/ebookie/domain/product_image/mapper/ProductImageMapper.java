package com.avad.ebookie.domain.product_image.mapper;

import com.avad.ebookie.domain.product_image.dto.response.ProductImageResponseDto;
import com.avad.ebookie.domain.product_image.entity.ProductImage;
import org.springframework.stereotype.Service;

@Service
public class ProductImageMapper {
    public ProductImageResponseDto toDto(ProductImage entity) {
        return ProductImageResponseDto.builder()
                .id(entity.getId())
                .imageOrder(entity.getImageOrder())
                .fileName(entity.getFileName())
                .originalName(entity.getOriginalName())
                .fileSize(entity.getFileSize())
                .build();
    }
}
