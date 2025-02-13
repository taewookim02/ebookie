package com.avad.ebookie.domain.product.dto.response;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
    private Long publisherId;
    private String publisherName;
    private Long categoryId;
    private String categoryName;
    private LocalDate publishedDate;
    private List<AuthorResponseDto> authors;


}
