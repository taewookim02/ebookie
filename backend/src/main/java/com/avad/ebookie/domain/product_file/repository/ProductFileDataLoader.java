package com.avad.ebookie.domain.product_file.repository;

import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_file.entity.ProductFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductFileDataLoader {
    private final ProductFileRepository productFileRepository;

    public List<ProductFile> generateProductFileData(List<Product> products) {
        log.info("상품 파일 더미데이터 체크");

        long count = productFileRepository.count();
        if (count != 0) {
            log.info("상품 파일 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        List<ProductFile> productFilesToSave = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            String originalName = i + ".pdf";
            ProductFile productFile = ProductFile.builder()
                    .fileSize(200L)
                    .parentProduct(products.get(i))
                    .originalName(originalName)
                    .fileName(originalName) // TODO: UUID 추가 후
                    .build();
            productFilesToSave.add(productFile);
        }
        return productFileRepository.saveAll(productFilesToSave);
    }
}
