package com.avad.ebookie.domain.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avad.ebookie.domain.product.entity.Product;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop15ByCategoryIdOrderByPublishedDateDesc(Long categoryId);

    List<Product> findTop15ByCategoryIdOrderBySoldDesc(Long categoryId);

    List<Product> findTop15ByCategoryIdAndIdNotOrderByPublishedDateDesc(Long id, Long id1);

    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);

    @Query("""
            SELECT p 
            FROM Product p
            ORDER BY p.sold DESC
            LIMIT 5
            """)
    List<Product> findTop5ProductsBySold();

    @Query("""
            SELECT DISTINCT p
            FROM Product p
            LEFT JOIN FETCH p.authors a
            LEFT JOIN FETCH a.author
            LEFT JOIN FETCH p.category c
            WHERE p.name LIKE %:query%
            OR a.author.name LIKE %:query%
            OR c.name LIKE %:query%
            """)
    List<Product> findAllBySearchQuery(@Param("query") String query);
}
