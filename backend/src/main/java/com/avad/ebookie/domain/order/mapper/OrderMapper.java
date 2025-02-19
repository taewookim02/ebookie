package com.avad.ebookie.domain.order.mapper;

import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderResponseDto toDto(Order entity) {
        return OrderResponseDto.builder()
                .orderId(entity.getId())
                .build();
    }
}
