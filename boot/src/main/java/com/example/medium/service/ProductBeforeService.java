package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.repository.ProductRepository;
import com.example.medium.repository.advice.ProductAdviceRepository;
import com.example.medium.service.dto.ProductSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBeforeService {

    private final ProductRepository productRepository;

    private final ProductAdviceRepository productAdviceRepository;

    public Optional<String> getValue(String value) {
        return productAdviceRepository.getValue(value);
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
