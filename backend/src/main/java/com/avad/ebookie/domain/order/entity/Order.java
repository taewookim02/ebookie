package com.avad.ebookie.domain.order.entity;

import com.avad.ebookie.domain.common.entity.BaseTimeEntity;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order_detail.entity.OrderDetail;
import com.avad.ebookie.domain.payment.entity.Payment;
import com.avad.ebookie.domain.status.entity.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "_order") // order는 MySql에서 있는 키워드라서 _ 붙힘
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    @JsonBackReference
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    @JsonBackReference
    private Status status;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Payment payment;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    private Double totalPrice;
}
