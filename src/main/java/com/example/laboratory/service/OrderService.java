package com.example.laboratory.service;

import com.example.laboratory.domain.Order;
import com.example.laboratory.domain.Product;
import com.example.laboratory.repository.OrderRepository;
import com.example.laboratory.repository.ProductRepository;
import com.example.laboratory.service.vo.OrderUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    @Transactional
    public int updateStatus(OrderUpdateVO vo) {

        if (vo.isBulk()) {

            Product product = productRepository.findById(vo.getProductId()).orElseThrow();

            product.getOrders().forEach(order -> order.changedStatus(vo.getStatus()));

            return product.getOrders().size();

        } else {

            Order order = orderRepository.findById(vo.getOrderId()).orElseThrow();

            order.changedStatus(vo.getStatus());

            return 1;
        }
    }

}
