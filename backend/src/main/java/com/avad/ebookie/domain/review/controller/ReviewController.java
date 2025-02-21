package com.avad.ebookie.domain.review.controller;

import com.avad.ebookie.domain.review.dto.request.ReviewCreateRequestDto;
import com.avad.ebookie.domain.review.dto.request.ReviewUpdateRequestDto;
import com.avad.ebookie.domain.review.dto.response.ReviewResponseDto;
import com.avad.ebookie.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(@Valid @RequestBody ReviewCreateRequestDto requestDto) {
        ReviewResponseDto responseDto = reviewService.createReview(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(@Valid @RequestBody ReviewUpdateRequestDto requestDto, @PathVariable("reviewId") Long reviewId) {
        ReviewResponseDto responseDto =reviewService.updateReview(reviewId, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
