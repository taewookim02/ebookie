package com.avad.ebookie.domain.product.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.common.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.cart.repository.CartRepository;
import com.avad.ebookie.domain.category.dto.response.CategoryProductsResponseDto;
import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.category.repository.CategoryRepository;
import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.liked_product.repository.LikedProductRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.entity.OrderStatus;
import com.avad.ebookie.domain.order.repository.OrderRepository;
import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductHomeResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductLibraryItemResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductLibraryListResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductListItemResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductListResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductRelatedResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductSearchResponseDto;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.mapper.ProductMapper;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.product_file.mapper.ProductFileMapper;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import com.avad.ebookie.domain.saved_product.repository.SavedProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final LikedProductRepository likedProductRepository;
    private final SavedProductRepository savedProductRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    private final ProductMapper productMapper;
    private final OrderRepository orderRepository;
    private final ProductFileMapper productFileMapper;


    public List<ProductDetailResponseDto> testProduct() {
        List<ProductDetailResponseDto> responses = productRepository.findAll()
                .stream()
                .map(product ->
                        productMapper.toDetailDto(product)
                ).collect(Collectors.toList());

        return responses;
    }

    @Transactional(readOnly = true)
    public ProductDetailResponseDto details(Long productId) {
        // 상품 정보 얻기
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
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

    @Transactional(readOnly = true)
    public ProductHomeResponseDto home() {
        // 이번 달 Top 5
//        LocalDate startDateOfMonth = LocalDate.now().withDayOfMonth(1);
//        LocalDate endDateOfMonth = YearMonth.now().atEndOfMonth();

        List<Product> topFiveProducts = productRepository.findTop5ProductsBySold();
        
        List<ProductRelatedResponseDto> topFiveDto = productMapper.toRelatedDtos(topFiveProducts);
        
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
                .topFiveSellingProducts(topFiveDto)
                .categoryProducts(collect)
                .build();
    }

    @Transactional(readOnly = true)
    public ProductListResponseDto getProducts(Pageable pageable) {
        System.out.println("pageable = " + pageable);
        // 페이지네이션 조회
        Page<Product> productsPagination = productRepository.findAll(pageable);
        int totalPages = productsPagination.getTotalPages();
        long totalElements = productsPagination.getTotalElements();

        List<ProductListItemResponseDto> relatedDtos = productMapper.toProductListDto(productsPagination.stream().collect(Collectors.toList()));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean isLoggedIn = authentication.getClass() == UsernamePasswordAuthenticationToken.class;


        return ProductListResponseDto.builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(totalPages)
                .totalElements(totalElements)
                .products(relatedDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public ProductListResponseDto getProductsByCategory(Long categoryId, Pageable pageable) {
        System.out.println("categoryId = " + categoryId);
        System.out.println("pageable = " + pageable);
        // find All by categoryId
        Page<Product> productsPagination = productRepository.findAllByCategoryId(categoryId, pageable);
        int totalPages = productsPagination.getTotalPages();
        long totalElements = productsPagination.getTotalElements();

        List<ProductListItemResponseDto> productListItemResponseDtos = productMapper.toProductListDto(productsPagination.stream().collect(Collectors.toList()));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

        return ProductListResponseDto.builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(totalPages)
                .totalElements(totalElements)
                .products(productListItemResponseDtos)
                .categoryName(category.getName())
                .build();
    }

    @Transactional(readOnly = true)
    public ProductLibraryListResponseDto getLibraryList(Pageable pageable) {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // 결제완료된 주문 구하기
        List<Order> paidOrders = orderRepository.findAllByMemberAndOrderStatus(loggedInMember, OrderStatus.PAID);

        // 주문을 업데이트 시간 기준으로 정렬
        paidOrders.sort((o1, o2) -> o2.getUpdatedAt().compareTo(o1.getUpdatedAt()));

        // 상품 목록 구하기 (순서 유지를 위해 LinkedHashSet 사용)
        Set<Product> allProducts = paidOrders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .map(orderDetail -> orderDetail.getProduct())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // 상품목록에 pagination 적용
        List<Product> paginatedProducts = allProducts.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        List<ProductLibraryItemResponseDto> libraryDtos = paginatedProducts.stream()
                .map(product -> ProductLibraryItemResponseDto.builder()
                        .productId(product.getId())
                        .title(product.getName())
                        .thumbnailUrl(product.getImages().get(0).getFileName())
                        .files(product.getFiles().stream()
                                .map(productFile -> productFileMapper.toDto(productFile))
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        int totalElements = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageable.getPageSize());
        int currentPage = pageable.getPageNumber();

        return ProductLibraryListResponseDto.builder()
                .currentPage(currentPage)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .libraryDtos(libraryDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public List<ProductSearchResponseDto> searchProducts(String query) {
        List<Product> products = productRepository.findAllBySearchQuery(query);

        List<ProductSearchResponseDto> productDtos = products.stream()
            .map(product -> ProductSearchResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .authorNames(product.getAuthors().stream()
                    .map(author -> author.getAuthor().getName())
                    .collect(Collectors.joining(", ")))
                .category(product.getCategory().getName())
                .build())
            .collect(Collectors.toList());

        return productDtos;
    }

    @Transactional(readOnly = true)
    public ProductListResponseDto getProductsBySearch(String query, Pageable pageable) {
        Page<Product> productsPagination = productRepository.findAllBySearchQuery(query, pageable);
        int totalPages = productsPagination.getTotalPages();
        long totalElements = productsPagination.getTotalElements();

        List<ProductListItemResponseDto> productListItemResponseDtos = productMapper.toProductListDto(productsPagination.stream().collect(Collectors.toList()));
        return ProductListResponseDto.builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(totalPages)
                .totalElements(totalElements)
                .products(productListItemResponseDtos)
                .categoryName(query)
                .build();
    }
}
