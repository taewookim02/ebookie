package com.avad.ebookie.domain.order.dto.response;

import com.avad.ebookie.domain.order.entity.OrderStatus;
import com.avad.ebookie.domain.order_detail.dto.response.OrderProductResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponseDto {
    private Long id;
    private OrderStatus orderStatus;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private List<OrderProductResponseDto> products;
}

