package com.avad.ebookie.domain.product_file.repository;

import com.avad.ebookie.domain.product_file.entity.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {
}
