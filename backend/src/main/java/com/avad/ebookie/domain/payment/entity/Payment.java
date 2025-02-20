package com.avad.ebookie.domain.payment.entity;

import com.avad.ebookie.domain.common.entity.BaseTimeEntity;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment extends BaseTimeEntity {

    @Id
    @Column(length = 128, name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Min(0)
    private Double paidPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
