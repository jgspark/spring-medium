package com.example.medium.service.vo.order.status;

import com.example.medium.domain.Product;
import com.example.medium.repository.OrderRepository;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.vo.order.OrderUpdate;

public final class BulkOrderStatusUpdate extends AbstractOrderStatusUpdate {

    BulkOrderStatusUpdate(ProductRepository productRepository, OrderRepository orderRepository) {
        super(productRepository, orderRepository);
    }

    @Override
    public int update(OrderUpdate vo) {
        Product product = productRepository.findById(vo.getProductId()).orElseThrow();

        product.getOrders().forEach(order -> order.changedStatus(vo.getStatus()));

        return product.getOrders().size();
    }
}
