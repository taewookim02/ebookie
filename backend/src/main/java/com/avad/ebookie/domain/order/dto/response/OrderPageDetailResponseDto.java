package com.avad.ebookie.domain.order.dto.response;

import com.avad.ebookie.domain.order_detail.dto.response.OrderDetailResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderPageDetailResponseDto {
    private List<OrderDetailResponseDto> orderDetailDtos;

}
