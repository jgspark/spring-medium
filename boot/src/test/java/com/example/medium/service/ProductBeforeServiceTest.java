package com.example.medium.service;

import com.example.medium.domain.Product;
import com.example.medium.repository.ProductRepository;
import com.example.medium.repository.cache.DefaultCache;
import com.example.medium.service.dto.ProductSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductBeforeServiceTest {

    private ProductBeforeService productBeforeService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DefaultCache defaultCache;

    @BeforeEach
    public void init() {
        productBeforeService = new ProductBeforeService(productRepository, defaultCache);
    }

    @Test
    @Order(1)
    public void created_success() {

        Product mock = Product.initBuilder().name("test..").build();

        given(productRepository.save(any())).willReturn(mock);

        ProductSaveRequest dto = new ProductSaveRequest("test..");

        Product entity = productBeforeService.created(dto);

        assertEquals(entity.getName(), mock.getName());
    }
}