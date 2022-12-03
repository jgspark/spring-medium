package com.example.medium.repository.advice;

import com.example.medium.domain.Product;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.cache.DefaultCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.medium.config.redis.RedisConfiguration.CacheTimePair.MIN_1;

@Repository
@RequiredArgsConstructor
public class ProductAdviceRepositoryImpl implements ProductAdviceRepository {

    private final String key = "product";

    private final ProductRepository productRepository;

    private final DefaultCache defaultCache;

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        final String relKey = key + "_" + id;
        return defaultCache.getAndPut(MIN_1, relKey, () -> productRepository.findById(id).orElse(null));
    }
}
