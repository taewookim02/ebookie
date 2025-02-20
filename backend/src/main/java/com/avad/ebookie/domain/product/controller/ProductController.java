package com.avad.ebookie.domain.product.controller;

import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.dto.response.ProductHomeResponseDto;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
