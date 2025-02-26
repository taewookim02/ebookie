package com.avad.ebookie.domain.review.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
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
