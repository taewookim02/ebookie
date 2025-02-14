package com.avad.ebookie.domain.review.mapper;

import com.avad.ebookie.domain.review.dto.response.ReviewResponseDto;
import com.avad.ebookie.domain.review.entity.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {

    public ReviewResponseDto toDto(Review entity) {
        return ReviewResponseDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .writerId(entity.getWriter().getId())
                .rating(entity.getRating())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
