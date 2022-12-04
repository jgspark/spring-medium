package com.example.medium.repository.cache;

import com.example.medium.domain.Product;

import java.util.Optional;

public interface ProductCacheRepository {

    Optional<String> getValue(String value);

    Optional<Product> findById(Long id);
}
