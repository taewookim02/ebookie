package com.avad.ebookie.domain.product_image.repository;

import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_image.entity.ProductImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductImageDataLoader {
    private final ProductImageRepository productImageRepository;

    public List<ProductImage> generateProductImageData(List<Product> products) {
        log.info("상품이미지 더미데이터 체크");

        long count = productImageRepository.count();
        if (count != 0) {
            log.info("상품이미지 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        // 기존 이미지
//        http://localhost:8080/assets/L%20(25)-DhpFOKh4.jpeg
//        originalName = L%20(25)-DhpFOKh4.jpeg
//        fileName = /assets/L%20(25)-DhpFOKh4.jpeg

        return null;
    }
}
