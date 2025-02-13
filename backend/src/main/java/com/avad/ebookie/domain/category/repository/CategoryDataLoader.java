package com.avad.ebookie.domain.category.repository;

import com.avad.ebookie.domain.category.entity.Category;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryDataLoader {
    private final CategoryRepository categoryRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class) // 스프링 컨테이너 모두 띄워졌을 때
    public void init() {
        log.info("카테고리 더미데이터 체크");
        // 데이터 있는지 확인
        long count = categoryRepository.count();
        if (count != 0) {
            log.info("카테고리 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return;
        }

        // 엔티티 저장용 리스트
        List<Category> categoriesToSave = new ArrayList<>();
        String[] defaultData = {"자격증", "백엔드", "프론트엔드", "게임", "AI"};
        for (String desc : defaultData) {
            Category category = Category
                    .builder()
                    .description(desc)
                    .build();
            categoriesToSave.add(category); // 저장용 리스트에 엔티티 추가
        }

        categoryRepository.saveAll(categoriesToSave); // db 저장
    }
}
