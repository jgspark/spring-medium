package com.example.laboratory.service.order;

import com.example.laboratory.repository.OrderRepository;
import com.example.laboratory.repository.ProductRepository;
import com.example.laboratory.service.vo.OrderUpdateVO;

public abstract class AbstractOrderStatusUpdate {

    protected final ProductRepository productRepository;

    protected final OrderRepository orderRepository;

    protected AbstractOrderStatusUpdate(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public abstract int update(OrderUpdateVO vo);

    static public AbstractOrderStatusUpdate of(boolean isBulk, ProductRepository productRepository, OrderRepository orderRepository) {
        if (isBulk) {
            return new BulkOrderStatusUpdate(productRepository, orderRepository);
        }
        return new SingleOrderStatusUpdate(productRepository, orderRepository);
    }

}
