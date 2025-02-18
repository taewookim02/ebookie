package com.avad.ebookie.domain.liked_product.controller;

import com.avad.ebookie.domain.liked_product.dto.request.BulkDeleteLikeRequestDto;
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

    @PostMapping("/liked/{productId}")
    public ResponseEntity<?> toggleLike(@PathVariable("productId") Long productId) {
        // 좋아요 토글 (없으면 생성, 있으면 삭제)
        String result = likedProductService.toggleLike(productId);

        // TODO: response dto?
        return ResponseEntity.ok(result);
    }


    @GetMapping("/liked")
    public ResponseEntity<?> getLikedProducts() {
        System.out.println("LikedProductController.getLikedProducts");

        List<LikedProductResponseDto> likedProductResponseDtos = likedProductService.getLikedProducts();
        return ResponseEntity.ok(likedProductResponseDtos);
    }

    @DeleteMapping("/liked/{productId}")
    public ResponseEntity<?> deleteLikes(@PathVariable("productId") Long productId) {
        likedProductService.deleteLike(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/liked/bulk")
    public ResponseEntity<?> bulkDeleteLikes(@RequestBody BulkDeleteLikeRequestDto bulkDeleteLikeRequestDto) {
        likedProductService.bulkDeleteLikes(bulkDeleteLikeRequestDto);
        return ResponseEntity.noContent().build();
    }



}
