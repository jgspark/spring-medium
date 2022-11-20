package com.example.laboratory.service.order;

import com.example.laboratory.domain.Product;
import com.example.laboratory.repository.OrderRepository;
import com.example.laboratory.repository.ProductRepository;
import com.example.laboratory.service.vo.OrderUpdateVO;

public final class BulkOrderStatusUpdate extends AbstractOrderStatusUpdate {

    BulkOrderStatusUpdate(ProductRepository productRepository, OrderRepository orderRepository) {
        super(productRepository, orderRepository);
    }

    @Override
    public int update(OrderUpdateVO vo) {
        Product product = productRepository.findById(vo.getProductId()).orElseThrow();

        product.getOrders().forEach(order -> order.changedStatus(vo.getStatus()));

        return product.getOrders().size();
    }
}
