package com.avad.ebookie.domain.product_file.controller;

import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import com.avad.ebookie.domain.product_file.service.ProductFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductFileController {
    private final ProductFileService productFileService;

    @GetMapping("/products/files/download/{productId}")
    public ResponseEntity<?> downloadProductFile(@PathVariable("productId") Long productId) {
        ProductFileResponseDto fileDto = productFileService.downloadProductFile(productId);
        return null;
    }
}
