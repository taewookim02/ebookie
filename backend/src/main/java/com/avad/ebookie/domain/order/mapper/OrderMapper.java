package com.avad.ebookie.domain.order.mapper;

import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order_detail.dto.response.OrderProductResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderResponseDto toDto(Order entity) {
        return OrderResponseDto.builder()
                .id(entity.getId())
                .build();
    }

    public OrderResponseDto toComplexDto(Order entity) {
        List<OrderProductResponseDto> productInfos = entity.getOrderDetails().stream()
            .map(detail -> OrderProductResponseDto.builder()
                .productId(detail.getProduct().getId())
                .productName(detail.getProduct().getName())
                .build())
            .collect(Collectors.toList());

        return OrderResponseDto.builder()
            .id(entity.getId())
            .orderStatus(entity.getOrderStatus())
            .totalPrice(entity.getTotalPrice())
            .createdAt(entity.getCreatedAt())
            .products(productInfos)
            .build();
    }
}
