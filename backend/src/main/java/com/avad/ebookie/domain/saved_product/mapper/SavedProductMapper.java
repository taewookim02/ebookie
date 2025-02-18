package com.avad.ebookie.domain.saved_product.mapper;

import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import com.avad.ebookie.domain.saved_product.dto.response.SavedProductResponseDto;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedProductMapper {

    public List<SavedProductResponseDto> toDtoList(List<SavedProduct> savedProducts) {
        return savedProducts.stream()
                .map(savedProduct -> toDto(savedProduct))
                .collect(Collectors.toList());
    }

    public SavedProductResponseDto toDto(SavedProduct savedProduct) {
        Product product = savedProduct.getProduct();

        // 작가 이름 구하기
        List<ProductAuthor> productAuthors = product.getAuthors();
        List<String> authorNames = new ArrayList<>();
        productAuthors
                .forEach(productAuthor -> {
                    Author author = productAuthor.getAuthor();
                    authorNames.add(author.getName());
                });

        // 상품 썸네일 이미지 구하기
        String fileName = product.getImages().get(0).getFileName();

        // 찜 상품 응답 dto
        return SavedProductResponseDto.builder()
                .id(savedProduct.getId())
                .productId(product.getId())
                .name(product.getName())
                .authorNames(String.join(", ", authorNames))
                .thumbnail(fileName)
                .build();
    }
}
