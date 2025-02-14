package com.avad.ebookie.domain.review.entity;

import com.avad.ebookie.domain.common.entity.BaseEntity;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review extends BaseEntity { // baseEntity: createdAt, updatedAt
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: check if min, max works with double
    @Min(1)
    @Max(10)
    private Double rating; // double

    @Column(length = 50)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Member writer;
}
