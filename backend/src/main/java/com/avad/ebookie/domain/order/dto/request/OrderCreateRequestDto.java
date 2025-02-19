package com.avad.ebookie.domain.order.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateRequestDto {
    private List<Long> productIds;
}
