package org.sa.appdemo.orderitem.service;


import org.sa.appdemo.orderitem.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO addItemToOrder(Long orderId, OrderItemDTO itemDTO);
    void removeItemFromOrder(Long orderId, Long itemId);
    void updateItemQuantity(Long orderId, Long itemId, Integer quantity);
    List<OrderItemDTO> getOrderItems(Long orderId);
}