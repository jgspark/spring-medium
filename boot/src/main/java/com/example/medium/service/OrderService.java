package com.example.medium.service;

import com.example.medium.repository.OrderRepository;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.vo.order.status.AbstractOrderStatusUpdate;
import com.example.medium.service.vo.order.OrderUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    @Transactional
    public int updateStatus(OrderUpdate vo) {
        AbstractOrderStatusUpdate orderStatus = AbstractOrderStatusUpdate.of(vo.isBulk(), productRepository, orderRepository);
        return orderStatus.update(vo);
    }

}
