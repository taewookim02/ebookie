package com.avad.ebookie.domain.order.controller;

import com.avad.ebookie.domain.order.dto.request.OrderCreateRequestDto;
import com.avad.ebookie.domain.order.dto.response.OrderPageDetailResponseDto;
import com.avad.ebookie.domain.order.dto.response.OrderResponseDto;
import com.avad.ebookie.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderPageDetailResponseDto> getOrderDetails(@PathVariable("orderId") Long orderId) {
        OrderPageDetailResponseDto dto = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }

}
