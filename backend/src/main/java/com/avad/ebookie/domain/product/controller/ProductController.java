package com.avad.ebookie.domain.product.controller;

import com.avad.ebookie.domain.product.dto.request.TestRequestDto;
import com.avad.ebookie.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {


    private final ProductService productService;

    // TODO: 필요한 기능 정리
}
