package com.avad.ebookie.domain.category.repository;

import com.avad.ebookie.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    List<Category> findAllByOrderByIdAsc();
}
