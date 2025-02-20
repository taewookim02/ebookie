package com.avad.ebookie.domain.product.repository;

import com.avad.ebookie.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop15ByCategoryIdOrderByPublishedDateDesc(Long categoryId);

    List<Product> findTop15ByCategoryIdOrderBySoldDesc(Long categoryId);

    List<Product> findTop15ByCategoryIdAndIdNotOrderByPublishedDateDesc(Long id, Long id1);
}
