package com.example.medium.domain;

import com.example.medium.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity(name = "products")
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Order> orders = new HashSet<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @Builder(builderMethodName = "initBuilder")
    private Product(String name) {
        this.name = name;
    }

    @Transient
    public void changeState(ProductStatus status) {
        this.status = status;
    }
}
