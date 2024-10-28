package org.sa.appdemo.order.dto;

import lombok.Data;
import org.sa.appdemo.orderitem.dto.CreateOrderItemRequest;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<CreateOrderItemRequest> items;
    private String paymentMethod;
}