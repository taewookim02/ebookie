package com.avad.ebookie.domain.review.repository;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewDataLoader {
    private final ReviewRepository reviewRepository;

    public List<Review> generateReviewData(List<Member> members, List<Product> products) {
        long count = reviewRepository.count();
        if (count != 0) {
            log.info("리뷰 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        List<Review> reviewsToSave = new ArrayList<>();
        Faker faker = new Faker(new Locale("ko"));
        Random random = new Random();

        // 리뷰 템플릿 - 다양한 한국어 리뷰 내용
        String[] reviewTemplates = {
                "책 내용이 %s 했어요. %s 부분이 특히 좋았습니다.",
                "%s 내용이라 읽기 쉬웠습니다. %s 점이 인상적이었어요.",
                "전반적으로 %s 한 책이에요. %s 면에서 만족스러웠습니다.",
                "%s 구성이 마음에 들었어요. 특히 %s 가 좋았습니다.",
                "처음에는 %s 했는데, 읽다 보니 %s 점이 매력적이었어요.",
                "%s 측면에서 배울 점이 많았어요. %s 부분이 인상 깊었습니다."
        };

        // 형용사/설명구 배열
        String[] adjectives = {
                "흥미로운", "체계적인", "깊이 있는", "명쾌한", "실용적인", "참신한",
                "독특한", "감동적인", "교육적인", "전문적인", "창의적인", "매력적인"
        };

        String[] aspects = {
                "작가의 시선", "문장력", "구성", "주제 의식",
                "분석 내용", "예시 활용", "실전 적용", "이론 설명", "사례 연구", "결론 도출"
        };

        // 각 멤버당 1~3개의 리뷰 생성
        for (Member member : members) {
            int reviewCount = random.nextInt(3) + 1; // 1~3개의 리뷰

            for (int i = 0; i < reviewCount; i++) {
                // 랜덤하게 책 선택
                Product randomProduct = products.get(random.nextInt(products.size()));

                // 랜덤 리뷰 템플릿 선택
                String template = reviewTemplates[random.nextInt(reviewTemplates.length)];
                String adj1 = adjectives[random.nextInt(adjectives.length)];
                String aspect = aspects[random.nextInt(aspects.length)];

                // 리뷰 내용 생성
                String content = String.format(template, adj1, aspect);

                // 별점 생성 (1~10)
                double rating = random.nextInt(10) + 1;

                // 리뷰 생성 시간 - 최근 6개월 내
                LocalDateTime createdAt = LocalDateTime.now().minusMonths(random.nextInt(6))
                        .minusDays(random.nextInt(30))
                        .minusHours(random.nextInt(24))
                        .minusMinutes(random.nextInt(60));

                Review review = Review.builder()
                        .writer(member)
                        .product(randomProduct)
                        .content(content)
                        .rating(rating)
//                        .createdAt(createdAt)
                        .build();

                reviewsToSave.add(review);
            }
        }

        return reviewRepository.saveAll(reviewsToSave);
    }
}