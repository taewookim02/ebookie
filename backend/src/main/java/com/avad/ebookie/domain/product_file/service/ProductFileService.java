package com.avad.ebookie.domain.product_file.service;

import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import com.avad.ebookie.domain.product_file.repository.ProductFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFileService {
    private final ProductFileRepository productFileRepository;
    @Value("${app.upload.file-dir}")
    private String path;

    public ProductFileResponseDto downloadProductFile(Long productId) {
        // get member
        System.out.println("path = " + path);
        System.out.println("productId = " + productId);

        // get set of paid products by member

        // get from the product

        // increase order_detail's downloadCount by 1

        // return ProductFileResponseDto
        return null;
    }
}
