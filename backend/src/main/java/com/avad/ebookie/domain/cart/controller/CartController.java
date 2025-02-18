package com.avad.ebookie.domain.cart.controller;

import com.avad.ebookie.domain.cart.dto.request.CartAddRequestDto;
import com.avad.ebookie.domain.cart.dto.response.CartResponseDto;
import com.avad.ebookie.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/{productId}")
    public ResponseEntity<?> toggleCart(@PathVariable("productId") Long productId) {
        String result = cartService.toggleCart(productId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestBody CartAddRequestDto cartAddRequestDto) {
        cartService.addToCart(cartAddRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartResponseDto>> getCartProducts() {
        List<CartResponseDto> cartResponseDtos = cartService.getCartProducts();
        return ResponseEntity.ok(cartResponseDtos);
    }
}
