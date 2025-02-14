package com.avad.ebookie.domain.product_image.controller;

import com.avad.ebookie.domain.product_image.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("image")
    public ResponseEntity<?> returnImage(@RequestParam("name") String imageName) {
        Resource resource = productImageService.returnImage(imageName);
        return ResponseEntity.ok(resource);
    }

}
