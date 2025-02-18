package com.avad.ebookie.domain.category.service;

import com.avad.ebookie.domain.category.dto.response.CategoryResponseDto;
import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.category.mapper.CategoryMapper;
import com.avad.ebookie.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponseDto> getCategories() {
//        List<Category> all = categoryRepository.findAllOrderByIdAsc();
        List<Category> all = categoryRepository.findAllByOrderByIdAsc();
        return all.stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }
}
