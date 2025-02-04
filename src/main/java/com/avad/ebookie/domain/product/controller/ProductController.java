package com.avad.ebookie.domain.product.controller;

import com.avad.ebookie.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

}
