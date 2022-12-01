package com.example.medium.service.vo.order.status;

import com.example.medium.repository.OrderRepository;
import com.example.medium.repository.ProductRepository;
import com.example.medium.service.vo.order.OrderUpdate;

public abstract class AbstractOrderStatusUpdate {

    protected final ProductRepository productRepository;

    protected final OrderRepository orderRepository;

    protected AbstractOrderStatusUpdate(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public abstract int update(OrderUpdate vo);

    static public AbstractOrderStatusUpdate of(boolean isBulk, ProductRepository productRepository, OrderRepository orderRepository) {
        if (isBulk) {
            return new BulkOrderStatusUpdate(productRepository, orderRepository);
        }
        return new SingleOrderStatusUpdate(productRepository, orderRepository);
    }

}
