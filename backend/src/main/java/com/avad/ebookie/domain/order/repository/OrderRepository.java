package com.avad.ebookie.domain.order.repository;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.entity.OrderStatus;
import com.avad.ebookie.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByMemberOrderByCreatedAtDesc(Member loggedInMember, Pageable pageable);


    Page<Order> findAllByMemberAndOrderStatusOrderByUpdatedAtDesc(Member loggedInMember, OrderStatus orderStatus, Pageable pageable);

    List<Order> findAllByMemberAndOrderStatus(Member loggedInMember, OrderStatus orderStatus);

}
