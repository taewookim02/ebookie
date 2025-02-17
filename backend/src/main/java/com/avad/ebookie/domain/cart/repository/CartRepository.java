package com.avad.ebookie.domain.cart.repository;

import com.avad.ebookie.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByProductIdAndMemberId(Long productId, Long id);

    void deleteByProductIdAndMemberId(Long productId, Long memberId);
}
