package com.avad.ebookie.domain.category.mapper;

import com.avad.ebookie.domain.category.dto.response.CategoryResponseDto;
import com.avad.ebookie.domain.category.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryResponseDto toDto(Category entity) {
        return CategoryResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
