package com.avad.ebookie.domain.order_detail.repository;

import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
