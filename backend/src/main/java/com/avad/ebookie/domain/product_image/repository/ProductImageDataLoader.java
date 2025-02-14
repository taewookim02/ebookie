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

        List<ProductImage> productImagesToSave = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            String originalName = i + ".jpeg"; // 1.jpeg, 2.jpeg, ...
            ProductImage productImage = ProductImage.builder()
                    .imageOrder(0)
                    .parentProduct(products.get(i))
                    .fileSize(150L)
                    .originalName(originalName) // UUID 추가 전
                    .fileName(originalName) // TODO: UUID 추가 후
                    .build();
            productImagesToSave.add(productImage);
        }
        return productImageRepository.saveAll(productImagesToSave);

        // 기존 이미지
//        http://localhost:8080/assets/L%20(25)-DhpFOKh4.jpeg
//        originalName = L%20(25)-DhpFOKh4.jpeg
//        fileName = /assets/L%20(25)-DhpFOKh4.jpeg
    }
}
