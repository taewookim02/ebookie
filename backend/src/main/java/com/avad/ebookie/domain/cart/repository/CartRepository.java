package com.avad.ebookie.domain.cart.repository;

import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByProductIdAndMemberId(Long productId, Long id);

    void deleteByProductIdAndMemberId(Long productId, Long memberId);

    List<Cart> findAllByMemberOrderByIdDesc(Member loggedInMember);

}
