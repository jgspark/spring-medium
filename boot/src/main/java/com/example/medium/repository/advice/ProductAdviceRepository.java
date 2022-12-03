package com.example.medium.repository.advice;

import com.example.medium.domain.Product;

import java.util.Optional;

public interface ProductAdviceRepository {

    Optional<String> getValue(String value);

    Optional<Product> findById(Long id);
}
