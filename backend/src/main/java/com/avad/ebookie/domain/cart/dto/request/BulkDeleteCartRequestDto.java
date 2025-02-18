package com.avad.ebookie.domain.cart.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BulkDeleteCartRequestDto {
    private List<Long> productIds;
}
