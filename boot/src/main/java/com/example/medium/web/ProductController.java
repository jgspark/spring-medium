package com.example.medium.web;

import com.example.medium.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String tes1(String value) {
        return productService.test(value);
    }
}

