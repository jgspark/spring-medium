package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.repository.ProductRepository;
import com.example.medium.repository.cache.ProductCacheRepository;
import com.example.medium.service.dto.ProductSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductBeforeService {

    private final ProductRepository productRepository;

    private final ProductCacheRepository productCacheRepository;

    public Optional<String> getValue(String value) {
        return productCacheRepository.getValue(value);
    }

    @Transactional(readOnly = true)
    public Optional<Product> getOne(Long productId) {
        return productCacheRepository.findById(productId);
    }

    @Transactional
    public Product created(ProductSaveRequest dto) {
        return productRepository.save(dto.toEntity());
    }
}
