package com.avad.ebookie.domain.product.service;

import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.cart.repository.CartRepository;
import com.avad.ebookie.domain.category.dto.response.CategoryProductsResponseDto;
import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.category.mapper.CategoryMapper;
import com.avad.ebookie.domain.category.repository.CategoryRepository;
import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.liked_product.repository.LikedProductRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductHomeResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductListResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductRelatedResponseDto;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.mapper.ProductMapper;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import com.avad.ebookie.domain.saved_product.repository.SavedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final LikedProductRepository likedProductRepository;
    private final SavedProductRepository savedProductRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

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
//        System.out.println("authentication = " + authentication); // AnonymousAuthenticationToken
//        System.out.println("authentication.getClass() = " + authentication.getClass());

        // 관련상품 추가
        List<Product> relatedProductsByCategory = productRepository.findTop15ByCategoryIdAndIdNotOrderByPublishedDateDesc(
            product.getCategory().getId(),
            product.getId()
        );
        List<ProductRelatedResponseDto> relatedProducts = productMapper.toRelatedDtos(relatedProductsByCategory);
        detailDto.setRelatedProducts(relatedProducts);

        return detailDto;
    }

    public ProductHomeResponseDto home() {
        // 이번 달 Top 5
        LocalDate startDateOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endDateOfMonth = YearMonth.now().atEndOfMonth();
        // TODO: orderRepository.findTop5ByCountProductIdBetween

        // 카테고리 별 추천 도서
        List<Category> categories = categoryRepository.findAll();

        List<CategoryProductsResponseDto> collect = categories.stream()
                .map(category -> {
                    return CategoryProductsResponseDto.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .products(productMapper
                                        .toRelatedDtos(
                                                productRepository
                                                .findTop15ByCategoryIdOrderBySoldDesc(category.getId())
                                        )
                            )
                            .build();
                }).collect(Collectors.toList());

        return ProductHomeResponseDto.builder()
                .topFiveSellingProducts(null)
                .categoryProducts(collect)
                .build();
    }

    public ProductListResponseDto getProducts(Pageable pageable) {
        System.out.println("pageable = " + pageable);
        Page<Product> all = productRepository.findAll(pageable);

        // should i return Page<Product>
        // or Page<ProductResponseDto>
        // or ProductListResponseDto?
        String test = "";
        System.out.println("all = " + all);
        return null;
    }
}
