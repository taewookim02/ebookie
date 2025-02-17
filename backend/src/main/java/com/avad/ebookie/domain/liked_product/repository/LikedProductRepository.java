package com.avad.ebookie.domain.liked_product.repository;

import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedProductRepository extends JpaRepository<LikedProduct, Long> {
    LikedProduct findByProductIdAndMemberId(Long productId, Long memberId);

    void deleteByProductIdAndMemberId(Long productId, Long memberId);
}
