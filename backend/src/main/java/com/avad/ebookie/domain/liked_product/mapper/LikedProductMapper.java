package com.avad.ebookie.domain.liked_product.mapper;

import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.liked_product.dto.response.LikedProductResponseDto;
import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedProductMapper {

    public List<LikedProductResponseDto> toDtoList(List<LikedProduct> likedProducts) {
        return likedProducts.stream()
                .map(likedProduct -> {
                    return toDto(likedProduct);
                })
                .collect(Collectors.toList());
    }

    public LikedProductResponseDto toDto(LikedProduct likedProduct) {
        Product product = likedProduct.getProduct();

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


        // 좋아요 상품 응답 dto
        return LikedProductResponseDto.builder()
                .id(likedProduct.getId())
                .productId(product.getId())
                .name(product.getName())
                .authorNames(String.join(", ", authorNames))
                .thumbnail(fileName)
                .build();
    }
}
