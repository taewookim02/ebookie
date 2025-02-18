package com.avad.ebookie.domain.saved_product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BulkDeleteSavedRequestDto {
    private List<Long> productIds;
}
