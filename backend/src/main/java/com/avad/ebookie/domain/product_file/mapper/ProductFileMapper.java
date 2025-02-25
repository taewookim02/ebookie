package com.avad.ebookie.domain.product_file.mapper;

import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import com.avad.ebookie.domain.product_file.entity.ProductFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductFileMapper {
    @Value("${app.upload.file-dir}")
    private String path;


    public ProductFileResponseDto toDto(ProductFile entity) {
        return ProductFileResponseDto.builder()
                .id(entity.getId())
                .productId(entity.getParentProduct().getId())
                .fileName(entity.getFileName())
                .originalName(entity.getOriginalName())
                .fullPath(path + entity.getFileName())
                .build();
    }

    public List<ProductFileResponseDto> toDtoList(List<ProductFile> files) {
        return files.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
