package com.avad.ebookie.domain.liked_product.repository;

import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedProductRepository extends JpaRepository<LikedProduct, Long> {
    LikedProduct findByProductIdAndMemberId(Long productId, Long memberId);

    void deleteByProductIdAndMemberId(Long productId, Long memberId);

    List<LikedProduct> findAllByMemberIdOrderByIdDesc(Long id);


    void deleteAllByMemberAndProductIdIn(Member loggedInMember, List<Long> productIds);
}
