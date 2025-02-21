package com.avad.ebookie.domain.payment.repository;

import com.avad.ebookie.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
