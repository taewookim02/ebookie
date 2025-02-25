package com.avad.ebookie.domain.order.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPageResponseDto {
    private List<OrderResponseDto> orders;
    private int totalPages;
    private long totalElements;
    private int currentPage;
}
