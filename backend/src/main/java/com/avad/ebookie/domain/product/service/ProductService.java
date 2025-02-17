package com.avad.ebookie.domain.product.service;

import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.cart.repository.CartRepository;
import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.liked_product.repository.LikedProductRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductRelatedResponseDto;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.mapper.ProductMapper;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import com.avad.ebookie.domain.saved_product.repository.SavedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final LikedProductRepository likedProductRepository;
    private final SavedProductRepository savedProductRepository;
    private final CartRepository cartRepository;
    private final ProductMapper productMapper;

    public List<ProductDetailResponseDto> testProduct() {
        List<ProductDetailResponseDto> responses = productRepository.findAll()
                .stream()
                .map(product ->
                        productMapper.toDetailDto(product)
                ).collect(Collectors.toList());

        return responses;
    }

    public ProductDetailResponseDto details(Long productId) {
        // 상품 정보 얻기
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(""));
        ProductDetailResponseDto detailDto = productMapper.toDetailDto(product);

        // 좋아요, 찜하기 정보 얻기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean isLoggedIn = authentication.getClass() == UsernamePasswordAuthenticationToken.class;
        if (isLoggedIn) {
            Member loggedInMember = (Member) authentication.getPrincipal();
            SavedProduct savedProduct = savedProductRepository.findByProductIdAndMemberId(productId, loggedInMember.getId());
            LikedProduct likedProduct = likedProductRepository.findByProductIdAndMemberId(productId, loggedInMember.getId());
            Cart cartProduct = cartRepository.findByProductIdAndMemberId(productId, loggedInMember.getId());
            detailDto.setIsSaved(savedProduct != null);
            detailDto.setIsLiked(likedProduct != null);
            detailDto.setIsInCart(cartProduct != null);
        }
        System.out.println("authentication = " + authentication); // AnonymousAuthenticationToken
        System.out.println("authentication.getClass() = " + authentication.getClass());

//        System.out.println("loggedInMember = " + loggedInMember);


        // 관련상품 추가
        List<Product> relatedProductsByCategory = productRepository.findTop15ByCategoryIdOrderByPublishedDateDesc(product.getCategory().getId());
        List<ProductRelatedResponseDto> relatedProducts = productMapper.toRelatedDtos(relatedProductsByCategory);
        detailDto.setRelatedProducts(relatedProducts);

        return detailDto;
    }
}
