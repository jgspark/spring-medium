package com.example.medium.service.vo;

import com.example.medium.enums.OrderStatus;
import com.example.medium.service.OrderService;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * 주문 업데이트에 대한 VO 입니다.
 * 주문에 대한 "상태 업테이트 관점" 이외에 다른 것을 담지 않습니다.
 *
 * @author newbalancer
 * @see OrderService#updateStatus(OrderUpdateVO)
 */
@Getter
public class OrderUpdateVO {

    /**
     * blocking no-arg constructor
     */
    private OrderUpdateVO() {
    }

    /**
     * 해당 생성자 외에는 사용하지 않습니다.
     *
     * @param productId 상품에 대한 아이디
     * @param status    주문에 대한 상태 변화 입니다.
     */
    @Builder
    private OrderUpdateVO(Long productId, Long orderId, @NotNull OrderStatus status) {
        this.productId = productId;
        this.status = status;
        this.orderId = orderId;
        Assert.notNull(status, "status is not null");
    }

    private Long productId;

    private Long orderId;

    private OrderStatus status;

    public boolean isBulk() {
        return orderId == null;
    }

}
