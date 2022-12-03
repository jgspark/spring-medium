package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.repository.ProductRepository;
import com.example.medium.repository.advice.ProductAdviceRepository;
import com.example.medium.service.cache.DefaultCache;
import com.example.medium.service.dto.ProductSaveRequest;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.medium.config.redis.RedisConfiguration.CacheTimePair.MIN_1;

@Service
@RequiredArgsConstructor
public class ProductBeforeService {

    private final ProductRepository productRepository;

    private final DefaultCache defaultCache;

    private final ProductAdviceRepository productAdviceRepository;

    private final String key = "product";

    public Optional<String> getValue(String value) {

        String relKey = key + "_" + value;

        return defaultCache.getAndPut(MIN_1, relKey, () -> value);
    }

    @Transactional(readOnly = true)
    public Optional<Product> getOne(Long productId) {
        return productAdviceRepository.findById(productId);
    }

    @Transactional
    public Product created(ProductSaveRequest dto) {
        return productRepository.save(dto.toEntity());
    }
}
