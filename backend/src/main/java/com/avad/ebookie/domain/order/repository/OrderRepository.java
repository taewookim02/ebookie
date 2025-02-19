package com.avad.ebookie.domain.order.repository;

import com.avad.ebookie.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
