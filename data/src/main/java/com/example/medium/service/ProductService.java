package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.enums.ProductStatus;
import com.example.medium.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 전파 레벨을 기본 값으로 줌으로 써 만약
     * 따른 Class 에서 사용을 하게 되면 트랜젝션 이 없을시 만들고
     * 있다면 해당 트랜잭션에 종속 시킨다.
     * <p>
     * 트랜잭션의 격리 레벨의 경우 1레벨로 설정을 해줌으로써 커밋된 데이터만 읽도록 설정
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Product getSaleProduct(Long productId) {
        return productRepository.findByIdAndStatus(productId, ProductStatus.SALE).orElseThrow(() -> new RuntimeException(String.format("not found product %d", productId)));
    }
}
