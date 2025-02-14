package com.avad.ebookie.domain.product_image.dto.response;

import lombok.*;

//@Data // TODO 모든 곳에서 Data 제거
@Getter
@Builder
//@NoArgsConstructor // TODO: Builder랑 관계없는 어노테이션 제거
//@AllArgsConstructor
public class ProductImageResponseDto {
    private Long id;
    private Integer imageOrder;
    private String fileName;
    private String originalName;
    private Long fileSize;
}
