package com.example.laboratory.service.dto;

import com.example.laboratory.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequestDTO {

    private OrderStatus status;

}
