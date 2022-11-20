package com.example.laboratory.service.order;

import com.example.laboratory.domain.Order;
import com.example.laboratory.repository.OrderRepository;
import com.example.laboratory.repository.ProductRepository;
import com.example.laboratory.service.vo.OrderUpdateVO;

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
