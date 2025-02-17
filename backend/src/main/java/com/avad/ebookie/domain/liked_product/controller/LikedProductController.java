package com.avad.ebookie.domain.liked_product.controller;

import com.avad.ebookie.domain.liked_product.service.LikedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
