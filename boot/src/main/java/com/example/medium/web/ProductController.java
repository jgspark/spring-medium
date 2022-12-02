package com.example.medium.web;

import com.example.medium.domain.Product;
import com.example.medium.service.ProductBeforeService;
import com.example.medium.service.dto.ProductSaveRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ProductController {

    private final ProductBeforeService productService;

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getId(@PathVariable Long id) {

        Optional<Product> data = productService.getOne(id);

        if (data.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data.get());
    }

    @PostMapping("product")
    public Product save(@RequestBody ProductSaveRequest dto) {
        return productService.created(dto);
    }

    @GetMapping("test")
    public ResponseEntity<String> tes1(String value) {

        Optional<String> data = productService.getValue(value);

        if (data.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data.get());
    }
}

