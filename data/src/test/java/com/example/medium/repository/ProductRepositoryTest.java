package com.example.medium.repository;

import com.example.medium.domain.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Order(1)
    public void save_success() {

        Product mock = Product.initBuilder().name("test..").build();

        Product entity = productRepository.save(mock);

        assertEquals(entity.getId(), mock.getId());
        assertEquals(entity.getName(), mock.getName());
    }

    @Nested
    @DisplayName("조회")
    public class Select {

        private Product mock;

        @BeforeEach
        public void init() {

            mock = productRepository.save(Product.initBuilder().name("test..").build());

            productRepository.flush();

        }

        @Test
        @Order(1)
        public void findById_success() {

            Product entity = productRepository.findById(mock.getId()).orElseThrow();

            assertEquals(entity.getId(), mock.getId());
            assertEquals(entity.getName(), mock.getName());
        }
    }

}