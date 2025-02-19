package com.avad.ebookie.domain.order.controller;

import com.avad.ebookie.domain.order.dto.request.OrderCreateRequestDto;
import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }

}
