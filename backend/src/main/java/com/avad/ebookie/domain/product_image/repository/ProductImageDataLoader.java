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

        java.util.Map<String, String> titleToImageMap = new java.util.HashMap<>();

        // 자격증 카테고리
        titleToImageMap.put("2024 정보처리기사 실기", "자격증_정처기실기.jpeg");
        titleToImageMap.put("리눅스마스터 2급 핵심정리", "자격증_리눅스.jpeg");
        titleToImageMap.put("AWS 공인 솔루션스 아키텍트", "자격증_AWS공인.jpeg");

        // 백엔드 카테고리
        titleToImageMap.put("스프링 부트와 AWS로 혼자 구현하는 웹 서비스", "백엔드_스프링부트AWS.jpeg");
        titleToImageMap.put("Java Spring 완벽 가이드", "백엔드_JAVASPRING.jpeg");
        titleToImageMap.put("실전! MSA 구축과 운영", "백엔드_MSA구축.jpeg");

        // 프론트엔드 카테고리
        titleToImageMap.put("모던 자바스크립트 Deep Dive", "프론트_모던자바스크립트.jpeg");
        titleToImageMap.put("React.js 실전 프로젝트", "프론트_리액트.jpeg");
        titleToImageMap.put("Next.js 마스터 클래스", "프론트_넥스트.jpeg");

        // 게임 카테고리
        titleToImageMap.put("유니티 개발자를 위한 디자인 패턴", "게임_유니티.jpeg");
        titleToImageMap.put("언리얼 엔진 5 블루프린트", "게임_언리얼.jpeg");
        titleToImageMap.put("게임 서버 프로그래밍 교과서", "게임_서버.jpeg");

        // AI 카테고리
        titleToImageMap.put("업무시간을 반으로 줄이는 AI 활용법", "AI_활용.jpeg");
        titleToImageMap.put("파이썬으로 배우는 딥러닝", "AI_파이썬.jpeg");
        titleToImageMap.put("실전 MLOps 가이드", "AI_MLOPS.jpeg");

        List<ProductImage> productImagesToSave = new ArrayList<>();

        for (Product product : products) {
            String productTitle = product.getName();
            String imageFileName = titleToImageMap.get(productTitle);

            if (imageFileName != null) {
                ProductImage productImage = ProductImage.builder()
                        .imageOrder(0)
                        .parentProduct(product)
                        .fileSize(50000L)
                        .originalName(imageFileName)
                        .fileName(imageFileName)
                        .build();
                productImagesToSave.add(productImage);
            } else {
                int index = products.indexOf(product) % 16;
                String fallbackFileName = index + ".jpeg";

                ProductImage productImage = ProductImage.builder()
                        .imageOrder(0)
                        .parentProduct(product)
                        .fileSize(25000L)
                        .originalName(fallbackFileName)
                        .fileName(fallbackFileName)
                        .build();
                productImagesToSave.add(productImage);
            }
        }

        return productImageRepository.saveAll(productImagesToSave);
    }
}