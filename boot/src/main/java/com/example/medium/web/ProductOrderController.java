package com.example.medium.web;

import com.example.medium.service.OrderService;
import com.example.medium.service.dto.OrderStatusRequest;
import com.example.medium.service.vo.order.OrderUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/{productId}")
@RequiredArgsConstructor
public class ProductOrderController {

    private final OrderService orderService;

    @PatchMapping("order/{orderId}")
    public int updateStatus(@PathVariable Long productId, @PathVariable Long orderId, @RequestBody OrderStatusRequest dto) {
        OrderUpdate vo = OrderUpdate.builder()
                .productId(productId)
                .orderId(orderId)
                .status(dto.getStatus())
                .build();

        return orderService.updateStatus(vo);
    }
}
