package com.avad.ebookie.domain.product.mapper;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.author.mapper.AuthorMapper;
import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.cart.repository.CartRepository;
import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.liked_product.repository.LikedProductRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductListItemResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductRelatedResponseDto;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import com.avad.ebookie.domain.product_image.dto.response.ProductImageResponseDto;
import com.avad.ebookie.domain.product_image.entity.ProductImage;
import com.avad.ebookie.domain.product_image.mapper.ProductImageMapper;
import com.avad.ebookie.domain.review.dto.response.ReviewResponseDto;
import com.avad.ebookie.domain.review.entity.Review;
import com.avad.ebookie.domain.review.mapper.ReviewMapper;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import com.avad.ebookie.domain.saved_product.repository.SavedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final AuthorMapper authorMapper;
    private final ReviewMapper reviewMapper;
    private final ProductImageMapper productImageMapper;
    private final SavedProductRepository savedProductRepository;
    private final LikedProductRepository likedProductRepository;
    private final CartRepository cartRepository;

    // 상품 상세 페이지 응답 DTO
    public ProductDetailResponseDto toDetailDto(Product entity) {
        // 저자, 리뷰 리스트
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        List<ProductImageResponseDto> productImageResponseDtos = new ArrayList<>();


        // ProductAuthor 테이블 접근
        for (ProductAuthor productAuthor : entity.getAuthors()) {
            // Author 테이블 접근
            Author author = productAuthor.getAuthor();

            // 저자 entity -> 저자응답Dto 변환
            AuthorResponseDto dto = authorMapper.toDto(author);
            authorResponseDtos.add(dto);
        }

        // 리뷰 dto 추가
        for (Review review : entity.getReviews()) {
            ReviewResponseDto dto = reviewMapper.toDto(review);
            reviewResponseDtos.add(dto);
        }

        // 이미지 dto 추가
        for (ProductImage image : entity.getImages()) {
            ProductImageResponseDto dto = productImageMapper.toDto(image);
            productImageResponseDtos.add(dto);
        }


        return ProductDetailResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .publisherId(entity.getPublisher().getId())
                .publisherName(entity.getPublisher().getName())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .publishedDate(entity.getPublishedDate())
                .discountRate(entity.getDiscountRate())
                .authors(authorResponseDtos) // 저자 정보 set
                .reviews(reviewResponseDtos) // 리뷰 정보 set
                .images(productImageResponseDtos) // 이미지 정보 set
                .build();
    }

    public List<ProductRelatedResponseDto> toRelatedDtos(List<Product> products) {

        List<ProductRelatedResponseDto> relatedDtos = products
                .stream()
                .map(product -> {
                    // 저자 이름만 구하기
                    List<String> authorNames = new ArrayList<>();
                    product.getAuthors().forEach(productAuthor -> {
                        Author author = productAuthor.getAuthor();
                        String name = author.getName();
                        authorNames.add(name);
                    });

                    // 썸네일 구하기
                    ProductImage productImage = product.getImages().get(0);
                    ProductImageResponseDto thumbnail = productImageMapper.toDto(productImage);


                    return ProductRelatedResponseDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .authorNames(String.join(", ", authorNames))
                            .publisherName(product.getPublisher().getName())
                            .price(product.getPrice())
                            .reviewCount(product.getReviews().size())
                            .reviewRating(product.getAverageRating()) // Product에 있는 게터 사용
                            .thumbnail(thumbnail)
                            .build();
                })
                .collect(Collectors.toList());
        return relatedDtos;
    }

    public List<ProductListItemResponseDto> toProductListDto(List<Product> products) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean isLoggedIn = authentication.getClass() == UsernamePasswordAuthenticationToken.class;


        List<ProductListItemResponseDto> relatedDtos = products
                .stream()
                .map(product -> {
                    // 저자 이름만 구하기
                    List<String> authorNames = new ArrayList<>();
                    product.getAuthors().forEach(productAuthor -> {
                        Author author = productAuthor.getAuthor();
                        String name = author.getName();
                        authorNames.add(name);
                    });

                    // 썸네일 구하기
                    ProductImage productImage = product.getImages().get(0);
                    ProductImageResponseDto thumbnail = productImageMapper.toDto(productImage);

                    ProductListItemResponseDto productListItemResponseDto = ProductListItemResponseDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .authorNames(String.join(", ", authorNames))
                            .publisherName(product.getPublisher().getName())
                            .price(product.getPrice())
                            .reviewCount(product.getReviews().size())
                            .reviewRating(product.getAverageRating()) // Product에 있는 게터 사용
                            .thumbnail(thumbnail)
                            .publishedDate(product.getPublishedDate())
                            .discountRate(product.getDiscountRate())
                            .sold(product.getSold())
                            .build();

                    // 좋아요, 찜, 장바구니 정보 구하기
                    if (isLoggedIn) {
                        Member loggedInMember = (Member) authentication.getPrincipal();
                        SavedProduct savedProduct = savedProductRepository.findByProductIdAndMemberId(product.getId(), loggedInMember.getId());
                        LikedProduct likedProduct = likedProductRepository.findByProductIdAndMemberId(product.getId(), loggedInMember.getId());
                        Cart cartProduct = cartRepository.findByProductIdAndMemberId(product.getId(), loggedInMember.getId());
                        productListItemResponseDto.setIsSaved(savedProduct != null);
                        productListItemResponseDto.setIsLiked(likedProduct != null);
                        productListItemResponseDto.setIsInCart(cartProduct != null);
                    }

                    return productListItemResponseDto;
                })
                .collect(Collectors.toList());
        return relatedDtos;
    }
}
