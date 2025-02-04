package com.avad.ebookie.domain.product.controller;

import com.avad.ebookie.domain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

}
