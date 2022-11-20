package com.example.laboratory.web;

import com.example.laboratory.service.OrderService;
import com.example.laboratory.service.dto.OrderStatusRequestDTO;
import com.example.laboratory.service.vo.OrderUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/{productId}")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PatchMapping("order/{orderId}")
    public int updateStatus(@PathVariable Long productId, @PathVariable Long orderId, @RequestBody OrderStatusRequestDTO dto) {
        OrderUpdateVO vo = OrderUpdateVO.builder()
                .productId(productId)
                .orderId(orderId)
                .status(dto.getStatus())
                .build();

        return orderService.updateStatus(vo);
    }
}
