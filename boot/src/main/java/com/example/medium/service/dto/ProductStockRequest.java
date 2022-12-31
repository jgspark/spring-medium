package com.example.medium.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockRequest {

    @Setter
    private Long productId;

    private Long orderCount;
}
