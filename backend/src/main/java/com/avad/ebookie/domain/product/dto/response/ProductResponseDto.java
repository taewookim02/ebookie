package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
    private Long publisherId;

    public static ProductResponseDto toDto(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .publisherId(entity.getPublisher().getId())
                .build();
    }
}
