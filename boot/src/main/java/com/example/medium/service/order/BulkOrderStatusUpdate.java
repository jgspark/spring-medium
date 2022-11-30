package com.example.medium.service.order;

import com.example.medium.domain.Product;
import com.example.medium.repository.OrderRepository;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.vo.OrderUpdateVO;

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
