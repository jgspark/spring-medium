package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.domain.Stock;
import com.example.medium.exception.NotfoundException;
import com.example.medium.exception.SoldOutException;
import com.example.medium.exception.StockLimitCountException;
import com.example.medium.repository.StockRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockPlusService {

    private final StockRepository stockRepository;

    /**
     * 전파레벨을 상위 트랜잭션이 있다면 위임
     * 만약 없다면 항위 트랜잭션을 새로 생성
     */
    @Transactional
    public Stock plus(@NotNull Product product, @NotNull Long orderCount) {

        Long productId = product.getId();

        Stock stock = stockRepository.findByProductId(productId).orElseThrow(() -> new NotfoundException(Stock.class));

        checkLimitCount(orderCount, stock.getLimitCount());

        plusCount(orderCount, stock);

        stockRepository.flush();

        return stock;
    }

    private void checkLimitCount(@NotNull Long orderCount, Long limitCount) {

        // 항상 제한된 값은 아님 만약 null 이면 예외 처리
        if (Objects.isNull(limitCount)) {
            return;
        }

        if (limitCount < orderCount) {
            throw new StockLimitCountException();
        }
    }

    private void plusCount(Long orderCount, Stock stock) {
        // 현재 수량
        Long newStockCount = orderCount + stock.getCount();

        // 오리지널 수량에 비해서 높으면 안됨
        Long originCount = stock.getOriginCount();

        if (newStockCount > originCount) {
            throw new SoldOutException();
        }

        stock.changeCount(newStockCount);
    }
}
