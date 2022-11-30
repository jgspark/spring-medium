package com.example.medium.service.order;

import com.example.medium.domain.Order;
import com.example.medium.repository.OrderRepository;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.vo.OrderUpdateVO;

public final class SingleOrderStatusUpdate extends AbstractOrderStatusUpdate {

    SingleOrderStatusUpdate(ProductRepository productRepository, OrderRepository orderRepository) {
        super(productRepository, orderRepository);
    }

    @Override
    public int update(OrderUpdateVO vo) {
        Order order = orderRepository.findById(vo.getOrderId()).orElseThrow();

        order.changedStatus(vo.getStatus());

        return 1;
    }
}
