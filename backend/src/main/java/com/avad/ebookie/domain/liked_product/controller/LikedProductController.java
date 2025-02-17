package com.avad.ebookie.domain.liked_product.controller;

import com.avad.ebookie.domain.liked_product.dto.response.LikedProductResponseDto;
import com.avad.ebookie.domain.liked_product.service.LikedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class LikedProductController {

    private final LikedProductService likedProductService;

    @PostMapping("/like/{productId}")
    public ResponseEntity<?> toggleLike(@PathVariable("productId") Long productId) {
        String result = likedProductService.toggleLike(productId);

        // TODO: response dto?
        return ResponseEntity.ok(result);
    }

    // TODO: liked?
    @GetMapping("/liked")
    public ResponseEntity<?> getLikedProducts() {
        System.out.println("LikedProductController.getLikedProducts");

        List<LikedProductResponseDto> likedProductResponseDtos = likedProductService.getLikedProducts();
        return ResponseEntity.ok().build();
    }



}
