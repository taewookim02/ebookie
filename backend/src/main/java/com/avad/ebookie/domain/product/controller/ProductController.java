package com.avad.ebookie.domain.product.controller;

import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductHomeResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductListResponseDto;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {


    private final ProductService productService;
    private final ProductRepository productRepository;

    // TODO: 필요한 기능 정리
    @GetMapping("/test")
    public ResponseEntity<List<ProductDetailResponseDto>> testProduct() {
        List<ProductDetailResponseDto> responses = productService.testProduct();
        return ResponseEntity.ok(responses);
    }

    // 홈페이지
    @GetMapping("/products/home")
    public ResponseEntity<ProductHomeResponseDto> home() {
        ProductHomeResponseDto productHomeResponseDto = productService.home();
        return ResponseEntity.ok(productHomeResponseDto);
    }

    // 상품 상세
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetailResponseDto> details(@PathVariable("productId") Long productId) {
        ProductDetailResponseDto productDetailResponseDto = productService.details(productId);
        return ResponseEntity.ok(productDetailResponseDto);
    }

    // 상품 목록 페이지네이션
    @GetMapping("/products")
    public ResponseEntity<ProductListResponseDto> getProducts(Pageable pageable) {
        ProductListResponseDto responseDtos = productService.getProducts(pageable);
        return ResponseEntity.ok(responseDtos);
    }

    // 카테고리별 상품
    @GetMapping("/products/categories/{categoryId}")
    public ResponseEntity<ProductListResponseDto> getProductsByCategory(@PathVariable("categoryId") Long categoryId, Pageable pageable) {
        ProductListResponseDto responseDtos = productService.getProductsByCategory(categoryId, pageable);
        return ResponseEntity.ok(responseDtos);
    }

}
