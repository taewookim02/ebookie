package com.avad.ebookie.domain.order_detail.repository;

import com.avad.ebookie.domain.order.entity.Order;
import com.avad.ebookie.domain.order.entity.OrderStatus;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderId(Long orderId);

    List<OrderDetail> findAllByOrderIn(List<Order> orders);

    @Modifying
    @Query("""
    UPDATE OrderDetail od 
    SET od.downloadCount = od.downloadCount + 1
    WHERE od.order.member.id = :memberId 
    AND od.product.id = :productId
    AND od.order.orderStatus = 'PAID'
    """)
    void incrementDownloadCount(@Param("memberId") Long memberId, @Param("productId") Long productId);
//    Page<OrderDetail> findByOrderMemberIdAndOrderOrderStatus(Long memberId, OrderStatus orderStatus, Pageable pageable);
}
