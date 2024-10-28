package org.sa.appdemo.orderitem.dto;

import lombok.Data;

@Data
public class CreateOrderItemRequest {
    private Long productId;
    private Integer quantity;
}