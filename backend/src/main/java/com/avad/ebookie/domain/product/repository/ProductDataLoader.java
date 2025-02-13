package com.avad.ebookie.domain.product.repository;

import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDataLoader {
    private final ProductRepository productRepository;

    public List<Product> generateProductData(List<Category> categories, List<Publisher> publishers) {
        log.info("상품 더미데이터 체크");

        // 데이터 넣을지 결정
        long count = productRepository.count();
        if (count != 0) {
            log.info("상품 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        // 카테고리별 책 제목
        Map<String, String[]> categoryBooks = Map.of(
                "자격증", new String[]{"2024 정보처리기사 실기", "리눅스마스터 2급 핵심정리", "AWS 공인 솔루션스 아키텍트"},
                "백엔드", new String[]{"스프링 부트와 AWS로 혼자 구현하는 웹 서비스", "Java Spring 완벽 가이드", "실전! MSA 구축과 운영"},
                "프론트엔드", new String[]{"모던 자바스크립트 Deep Dive", "React.js 실전 프로젝트", "Next.js 마스터 클래스"},
                "게임", new String[]{"유니티 개발자를 위한 디자인 패턴", "언리얼 엔진 5 블루프린트", "게임 서버 프로그래밍 교과서"},
                "AI", new String[]{"업무시간을 반으로 줄이는 AI 활용법", "파이썬으로 배우는 딥러닝", "실전 MLOps 가이드"}
        );

        // 상품 저장용 리스트
        List<Product> productsToSave = new ArrayList<>();

        // 더미데이터 생성용
        Faker faker = new Faker(new Locale("ko"));

        for (int i = 0; i < publishers.size(); i++) {
            Publisher publisher = publishers.get(i);
            Category category = categories.get(i % categories.size());

            // 해당 카테고리의 책 제목 배열 가져오기
            String[] bookTitles = categoryBooks.get(category.getName());
            if (bookTitles != null) {
                String title = bookTitles[i % bookTitles.length];
                LocalDate randomPastDate = faker.timeAndDate().past(365, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).toLocalDate();
                Double randomDiscountRate = faker.number().randomDouble(1, 0, 100);

                Product product = Product.builder()
                        .name(title)
                        .price(25000d + (i * 1000)) // 가격 다양화
                        .publisher(publisher)
                        .category(category)
                        .publishedDate(randomPastDate) // 랜덤 LocalDate
                        .discountRate(randomDiscountRate) // 랜덤 할인율 (0~100)
                        .build();
                productsToSave.add(product);
            }
        }

        return productRepository.saveAll(productsToSave);
    }
}