package com.avad.ebookie.domain.saved_product.controller;

import com.avad.ebookie.domain.saved_product.service.SavedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SavedProductController {

    private final SavedProductService savedProductService;

    @PostMapping("/save/{productId}")
    public ResponseEntity<?> saveProduct(@PathVariable("productId") Long productId) {
        String result = savedProductService.toggleSave(productId);
        
        // TODO: response dto

        return ResponseEntity.ok(result);
    }
}
