package com.avad.ebookie.domain.saved_product.repository;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedProductRepository extends JpaRepository<SavedProduct, Long> {
    SavedProduct findByProductIdAndMemberId(Long productId, Long memberId);

    void deleteByProductIdAndMemberId(Long productId, Long memberId);

    List<SavedProduct> findAllByMemberIdOrderByIdDesc(Long id);

    void deleteAllByMemberAndProductIdIn(Member loggedInMember, List<Long> productIds);
}
