package com.avad.ebookie.domain.product.mapper;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.author.mapper.AuthorMapper;
import com.avad.ebookie.domain.product.dto.response.ProductResponseDto;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final AuthorMapper authorMapper;

    public ProductResponseDto toDto(Product entity) {
        // 저자 정보 리스트
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();

        // ProductAuthor 테이블 접근
        for (ProductAuthor productAuthor : entity.getAuthors()) {
            // 저자 정보 구하기
            Author author = productAuthor.getAuthor();

            // 저자 entity -> 저자응답Dto 변환
            AuthorResponseDto dto = authorMapper.toDto(author);
            authorResponseDtos.add(dto);
        }

        return ProductResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .publisherId(entity.getPublisher().getId())
                .publisherName(entity.getPublisher().getName())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .publishedDate(entity.getPublishedDate())
                .authors(authorResponseDtos) // 저자 정보 set
                .build();
    }
}
