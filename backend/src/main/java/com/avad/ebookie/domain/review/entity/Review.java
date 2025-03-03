package com.avad.ebookie.domain.review.entity;

import com.avad.ebookie.domain.common.entity.BaseTimeEntity;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review extends BaseTimeEntity { // baseEntity: createdAt, updatedAt
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: check if min, max works with double
    @Min(1)
    @Max(5)
    private Double rating; // double

    @Column(length = 50)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Member writer;


    public void setContent(String content) {
        this.content = content;
    }
}
