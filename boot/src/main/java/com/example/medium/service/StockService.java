package com.example.medium.service;

import com.example.medium.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductService productService;

    private final StockPlusService stockPlusService;

    @Transactional
    public Product orderByProduct(Long productId, Long orderCount) {

        Product product = productService.getSaleProduct(productId);

        stockPlusService.plus(product, orderCount);

        return product;
    }

}
