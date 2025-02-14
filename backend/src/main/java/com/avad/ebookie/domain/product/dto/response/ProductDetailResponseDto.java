package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.review.dto.response.ReviewResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Long publisherId;
    private String publisherName;
    private Long categoryId;
    private String categoryName;
    private LocalDate publishedDate;
    private Double discountRate;
    private List<ReviewResponseDto> reviews;
    private List<AuthorResponseDto> authors;

    // TODO:
    // 1. private List<ProductRelatedResponseDto> relatedProducts;
    // 2. Review


}
