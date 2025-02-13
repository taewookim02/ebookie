package com.avad.ebookie.domain.product.repository;

import com.avad.ebookie.config.event.PublisherDataLoadedEvent;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import com.avad.ebookie.domain.publisher.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDataLoader {
    private final ProductRepository productRepository;
    private final PublisherRepository publisherRepository;

    @Transactional
    @EventListener(PublisherDataLoadedEvent.class) // 출판사 더미데이터 있을 때 실행
    public void init() {
        log.info("상품 더미데이터 체크");

        long count = productRepository.count();
        if (count != 0) {
            log.info("상품 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return;
        }

        List<Product> productsToSave = new ArrayList<>();
        for (Publisher publisher : publisherRepository.findAll()) {
            log.info(publisher.toString());
            Product product = Product.builder()
                    .name("업무시간을 반으로 줄이는 AI 활용법")
                    .price(22000L)
                    .publisher(publisher)
                    .build();

//            TEST:
            if (publisher.getId() == 1) {
                Product product2 = Product.builder()
                        .name("업222")
                        .price(223000L)
                        .publisher(publisher)
                        .build();
                productsToSave.add(product2);
            }
            productsToSave.add(product);
        }
        productRepository.saveAll(productsToSave);
    }
}
