package com.example.medium.dto;

import com.example.medium.enums.ProductStatus;

public interface ProductStock {
    Long getId();

    String getName();

    ProductStatus getStatus();
}
