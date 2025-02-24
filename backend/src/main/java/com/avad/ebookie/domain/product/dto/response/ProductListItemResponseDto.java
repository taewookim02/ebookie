package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.product_image.dto.response.ProductImageResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProductListItemResponseDto {
    private Long id;
    private String name;
    private String authorNames;
    private String publisherName;
    private Double price;
    private Double reviewRating;
    private Integer reviewCount;
    private Double discountRate;
    private LocalDate publishedDate;
    private Long sold;
    private Boolean isSaved;
    private Boolean isLiked;
    private Boolean isInCart;
    private ProductImageResponseDto thumbnail;
}
