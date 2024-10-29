package org.sa.appdemo.order.service;

import org.sa.appdemo.order.dto.OrderDTO;
import org.sa.appdemo.order.dto.CreateOrderRequest;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(CreateOrderRequest request);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
}