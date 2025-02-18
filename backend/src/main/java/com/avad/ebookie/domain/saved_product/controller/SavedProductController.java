package com.avad.ebookie.domain.saved_product.controller;

import com.avad.ebookie.domain.liked_product.dto.response.LikedProductResponseDto;
import com.avad.ebookie.domain.saved_product.dto.request.BulkDeleteSavedRequestDto;
import com.avad.ebookie.domain.saved_product.dto.response.SavedProductResponseDto;
import com.avad.ebookie.domain.saved_product.service.SavedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SavedProductController {

    private final SavedProductService savedProductService;

    @PostMapping("/saved/{productId}")
    public ResponseEntity<?> toggleSave(@PathVariable("productId") Long productId) {
        String result = savedProductService.toggleSave(productId);

        // TODO: response dto

        return ResponseEntity.ok(result);
    }

    @GetMapping("/saved")
    public ResponseEntity<List<SavedProductResponseDto>> getSavedProducts() {
        System.out.println("SavedProductController.getSavedProducts");
        List<SavedProductResponseDto> savedProductResponseDtos = savedProductService.getSavedProducts();
        return ResponseEntity.ok(savedProductResponseDtos);
    }

    @DeleteMapping("/saved/{productId}")
    public ResponseEntity<?> deleteSaved(@PathVariable("productId") Long productId) {
        savedProductService.deleteSaved(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/saved/bulk")
    public ResponseEntity<?> bulkDeleteSaved(@RequestBody BulkDeleteSavedRequestDto bulkDeleteSavedRequestDto) {
        savedProductService.bulkDeleteSaved(bulkDeleteSavedRequestDto);
        return ResponseEntity.noContent().build();
    }
}
