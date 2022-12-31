package com.example.medium.web;

import com.example.medium.domain.Product;
import com.example.medium.service.StockService;
import com.example.medium.service.dto.ProductStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PatchMapping("product/{productId}/stock")
    public Product orderProduct(@PathVariable Long productId, @RequestBody ProductStockRequest dto) {
        dto.setProductId(productId);
        return stockService.orderByProduct(dto.getProductId(), dto.getOrderCount());
    }
}
