package com.avad.ebookie.domain.order.repository;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByMemberOrderByCreatedAtDesc(Member loggedInMember, Pageable pageable);
}
