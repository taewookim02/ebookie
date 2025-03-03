package com.avad.ebookie.domain.liked_product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
public class BulkDeleteLikeRequestDto {
    private List<Long> productIds;
}
