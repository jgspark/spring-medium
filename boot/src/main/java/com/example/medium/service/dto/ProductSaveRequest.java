package com.example.medium.service.dto;

import com.example.medium.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveRequest {

    private String name;

    public Product toEntity() {
        return Product.initBuilder()
            .name(name)
            .build();
    }
}
