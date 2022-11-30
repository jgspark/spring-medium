package com.example.medium.service.dto;

import com.example.medium.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequest {

    private OrderStatus status;

}
