package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.domain.Stock;
import com.example.medium.dto.ProductStock;
import com.example.medium.enums.ProductStatus;
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

        Stock stock = stockPlusService.plus(product, orderCount);

        if (stock.isSoldOutAble()) {
            product.changeState(ProductStatus.SOLD_OUT);
        }

        return product;
    }

}
