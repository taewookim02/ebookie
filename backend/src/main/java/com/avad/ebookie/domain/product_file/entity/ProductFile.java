package com.avad.ebookie.domain.product_file.entity;

import com.avad.ebookie.domain.common.entity.BaseTimeEntity;
import com.avad.ebookie.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class ProductFile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String originalName;
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product parentProduct;

}
