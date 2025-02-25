package com.avad.ebookie.domain.order_detail.repository;

import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.entity.OrderStatus;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderId(Long orderId);

    List<OrderDetail> findAllByOrderIn(List<Order> orders);

//    Page<OrderDetail> findByOrderMemberIdAndOrderOrderStatus(Long memberId, OrderStatus orderStatus, Pageable pageable);
}
