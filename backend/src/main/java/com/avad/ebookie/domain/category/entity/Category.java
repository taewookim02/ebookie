package com.avad.ebookie.domain.category.entity;

import com.avad.ebookie.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();
}

/*
*     create table category (
        id bigint not null auto_increment,
        description varchar(32),
        primary key (id)
    ) engine=InnoDB
* */
