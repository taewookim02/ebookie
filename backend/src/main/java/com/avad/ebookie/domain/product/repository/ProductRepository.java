package com.avad.ebookie.domain.product.repository;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
}
