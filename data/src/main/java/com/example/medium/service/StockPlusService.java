package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.domain.Stock;
import com.example.medium.exception.SoldOutException;
import com.example.medium.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StockPlusService {

    private final StockRepository stockRepository;

    /**
     * 전파레벨을 상위 트랜잭션이 있다면 위임
     * 만약 없다면 항위 트랜잭션을 새로 생성
     */
    @Transactional
    public void plus(Product product, Long orderCount) {

        Long productId = product.getId();

        int cnt = 0;

        while (cnt < 5) {

            try {

                Stock stock = stockRepository.findByProductId(productId).orElseThrow();

                plusCount(orderCount, stock);

            } catch (SoldOutException e) {
                e.printStackTrace();
                cnt++;
            }
        }
    }

    private void plusCount(Long orderCount, Stock stock) {
        // 현재 수량
        long newStockCount = orderCount + stock.getCount();

        // 오리지널 수량에 비해서 높으면 안됨
        Long originCount = stock.getOriginCount();

        if (newStockCount > originCount) {
            throw new SoldOutException();
        }

        stock.changeCount(newStockCount);
    }
}
