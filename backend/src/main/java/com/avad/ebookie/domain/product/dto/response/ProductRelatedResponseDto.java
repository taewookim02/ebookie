package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.product_image.dto.response.ProductImageResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductRelatedResponseDto {
    private Long id;
    private String name;
    private String authorNames;
    private String publisherName;
    private Double price;
    private Double reviewRating;
    private Integer reviewCount;
    private ProductImageResponseDto thumbnail;
}
