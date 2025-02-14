package com.avad.ebookie.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long writerId;
    private String writerEmail;
    private Double rating;
    private String content;
    private LocalDateTime createdAt;
}
