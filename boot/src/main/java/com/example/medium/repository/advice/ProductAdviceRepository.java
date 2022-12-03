package com.example.medium.repository.advice;

import com.example.medium.domain.Product;

import java.util.Optional;

public interface ProductAdviceRepository {
    Optional<Product> findById(Long id);
}
