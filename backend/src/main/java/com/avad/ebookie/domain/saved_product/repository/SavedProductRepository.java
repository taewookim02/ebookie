package com.avad.ebookie.domain.saved_product.repository;

import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedProductRepository extends JpaRepository<SavedProduct, Long> {
    SavedProduct findByProductIdAndMemberId(Long productId, Long id);

    void deleteByProductIdAndMemberId(Long id, Long id1);
}
