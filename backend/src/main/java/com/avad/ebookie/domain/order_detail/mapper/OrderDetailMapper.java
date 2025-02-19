package com.avad.ebookie.domain.order_detail.mapper;

import com.avad.ebookie.domain.order_detail.dto.response.OrderDetailResponseDto;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import com.avad.ebookie.domain.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailMapper {

    public List<OrderDetailResponseDto> toDtoList(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(orderDetail -> toDto(orderDetail))
                .collect(Collectors.toList());
    }

    private OrderDetailResponseDto toDto(OrderDetail entity) {
        Product product = entity.getProduct();
        String thumbnailFileName = product.getImages().get(0).getFileName();
        return OrderDetailResponseDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .quantity(entity.getQuantity())
                .originalPrice(product.getPrice())
                .discountRatePercentage(product.getDiscountRate())
                .thumbnail(thumbnailFileName)
                .build();
    }
}
