package com.example.medium.service.vo.order.status;

import com.example.medium.domain.Order;
import com.example.medium.repository.OrderRepository;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.vo.order.OrderUpdate;
import com.example.medium.service.vo.order.status.AbstractOrderStatusUpdate;

public final class SingleOrderStatusUpdate extends AbstractOrderStatusUpdate {

    SingleOrderStatusUpdate(ProductRepository productRepository, OrderRepository orderRepository) {
        super(productRepository, orderRepository);
    }

    @Override
    public int update(OrderUpdate vo) {
        Order order = orderRepository.findById(vo.getOrderId()).orElseThrow();

        order.changedStatus(vo.getStatus());

        return 1;
    }
}
