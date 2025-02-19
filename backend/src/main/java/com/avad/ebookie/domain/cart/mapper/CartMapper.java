package com.avad.ebookie.domain.cart.mapper;

import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.cart.dto.response.CartResponseDto;
import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {
    public List<CartResponseDto> toDtoList(List<Cart> cartList) {

        return cartList.stream()
                .map(cart -> toDto(cart))
                .collect(Collectors.toList());
    }

    private CartResponseDto toDto(Cart entity) {
        Product product = entity.getProduct();

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

        return CartResponseDto.builder()
                .id(entity.getId())
                .productId(product.getId())
                .name(product.getName())
                .authorNames(String.join(", ", authorNames))
                .thumbnail(fileName)
                .quantity(entity.getQuantity())
                .originalPrice(product.getPrice())
                .discountRatePercentage(product.getDiscountRate())
                .build();
    }
}
