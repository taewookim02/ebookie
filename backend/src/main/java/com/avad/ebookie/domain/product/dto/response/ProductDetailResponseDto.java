package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.product_image.dto.response.ProductImageResponseDto;
import com.avad.ebookie.domain.review.dto.response.ReviewResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

//@Data
@Getter
@Setter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Long publisherId;
    private String publisherName;
    private Long categoryId;
    private String categoryName;
    private LocalDate publishedDate;
    private Double discountRate;
    private Boolean isLiked;
    private Boolean isSaved;
    private Boolean isInCart;
    private List<ReviewResponseDto> reviews;
    private List<AuthorResponseDto> authors;
    private List<ProductImageResponseDto> images;
    private List<ProductRelatedResponseDto> relatedProducts;

    // TODO:
    // 1. private List<ProductRelatedResponseDto> relatedProducts;
    // 2. Review


}
