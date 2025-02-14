package com.avad.ebookie.domain.product_image.repository;

import com.avad.ebookie.domain.product_image.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
