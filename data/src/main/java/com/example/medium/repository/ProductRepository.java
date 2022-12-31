package com.example.medium.repository;

import com.example.medium.domain.Product;
import com.example.medium.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndStatus(Long aLong, ProductStatus status);
}
