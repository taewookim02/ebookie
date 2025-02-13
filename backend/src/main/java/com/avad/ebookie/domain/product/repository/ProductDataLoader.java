package com.avad.ebookie.domain.product.repository;

import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDataLoader {
    private final ProductRepository productRepository;

    public List<Product> generateProductFromPublisher(List<Publisher> publishers) {
        log.info("상품 더미데이터 체크");

        long count = productRepository.count();
        if (count != 0) {
            log.info("상품 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }


        List<Product> productsToSave = new ArrayList<>();
        for (int i = 0; i < publishers.size(); i++) {
            Publisher publisher = publishers.get(i);

            Product product = Product.builder()
                    .name("업무시간을 반으로 줄이는 AI 활용법")
                    .price(22000L)
                    .publisher(publisher)
                    .build();
            productsToSave.add(product);
        }
        return productRepository.saveAll(productsToSave);
    }
}
