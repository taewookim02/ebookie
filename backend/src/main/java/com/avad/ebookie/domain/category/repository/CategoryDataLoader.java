package com.avad.ebookie.domain.category.repository;

import com.avad.ebookie.domain.category.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryDataLoader {
    private final CategoryRepository categoryRepository;

    public List<Category> generateCategoryData() {
        long count = categoryRepository.count();
        if (count != 0) {
            log.info("카테고리 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
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

        return categoryRepository.saveAll(categoriesToSave); // db 저장
    }
}
