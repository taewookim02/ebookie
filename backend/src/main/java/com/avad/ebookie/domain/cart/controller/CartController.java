package com.avad.ebookie.domain.cart.controller;

import com.avad.ebookie.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
