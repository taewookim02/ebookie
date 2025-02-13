package com.avad.ebookie.domain.product_author.repository;

import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAuthorRepository extends JpaRepository<ProductAuthor, Long> {
}
