package com.avad.ebookie.domain.review.service;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.review.dto.request.ReviewCreateRequestDto;
import com.avad.ebookie.domain.review.dto.request.ReviewUpdateRequestDto;
import com.avad.ebookie.domain.review.dto.response.ReviewResponseDto;
import com.avad.ebookie.domain.review.entity.Review;
import com.avad.ebookie.domain.review.mapper.ReviewMapper;
import com.avad.ebookie.domain.review.repository.ReviewRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponseDto createReview(ReviewCreateRequestDto requestDto) {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("product not found: " + requestDto.getProductId()));


        Review review = Review.builder()
                .content(requestDto.getContent())
                .rating(Double.valueOf(requestDto.getRating()))
                .writer(loggedInMember)
                .product(product)
                .build();
        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toDto(savedReview);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, @Valid ReviewUpdateRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("review not found: " + reviewId));
        review.setContent(requestDto.getContent());
        reviewRepository.save(review);
        return reviewMapper.toDto(review);

    }
}
