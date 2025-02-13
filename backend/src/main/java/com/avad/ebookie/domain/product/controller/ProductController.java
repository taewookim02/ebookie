package com.avad.ebookie.domain.product.controller;

import com.avad.ebookie.domain.product.dto.response.ProductResponseDto;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {



    private final ProductService productService;
    private final ProductRepository productRepository;

    // TODO: 필요한 기능 정리
    @GetMapping("test/product")
    public ResponseEntity<List<ProductResponseDto>> testProduct() {
//        List<ProductResponseDto> responses = productRepository.findAll().stream().map(product ->
//                ProductResponseDto.toDto(product)
//        ).collect(Collectors.toList());
        List<ProductResponseDto> responses = productService.testProduct();
        return ResponseEntity.ok(responses);
    }
}
