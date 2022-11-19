package com.example.laboratory.service;

import com.example.laboratory.repository.OrderRepository;
import com.example.laboratory.repository.ProductRepository;
import com.example.laboratory.service.vo.OrderUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    @Transactional
    public int updateStatus(OrderUpdateVO orderUpdateVO) {

        if (orderUpdateVO.isBulk()) {

        } else {

        }

        return 0;
    }

}
