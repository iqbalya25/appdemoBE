package org.sa.appdemo.orderitem.service.impl;

import lombok.RequiredArgsConstructor;
import org.sa.appdemo.exception.ResourceNotFoundException;
import org.sa.appdemo.orderitem.dto.OrderItemDTO;
import org.sa.appdemo.order.entity.Order;
import org.sa.appdemo.orderitem.entity.OrderItem;
import org.sa.appdemo.orderitem.repository.OrderItemRepository;
import org.sa.appdemo.order.repository.OrderRepository;
import org.sa.appdemo.orderitem.service.OrderItemService;
import org.sa.appdemo.product.entity.Product;
import org.sa.appdemo.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderItemDTO addItemToOrder(Long orderId, OrderItemDTO itemDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Check if product already exists in order
        OrderItem existingItem = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Update existing item
            existingItem.setQuantity(existingItem.getQuantity() + itemDTO.getQuantity());
            existingItem.calculateSubtotal();
            order.recalculateTotal();
            orderRepository.save(order);
            return convertToDTO(existingItem);
        }

        // Create new item
        OrderItem newItem = new OrderItem();
        newItem.setOrder(order);
        newItem.setProduct(product);
        newItem.setQuantity(itemDTO.getQuantity());
        newItem.setUnitPrice(product.getPrice());
        newItem.calculateSubtotal();

        order.addItem(newItem);
        orderRepository.save(order);

        return convertToDTO(newItem);
    }

    @Override
    @Transactional
    public void removeItemFromOrder(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));

        order.removeItem(item);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateItemQuantity(Long orderId, Long itemId, Integer quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));

        if (quantity <= 0) {
            order.removeItem(item);
        } else {
            item.setQuantity(quantity);
            item.calculateSubtotal();
            order.recalculateTotal();
        }

        orderRepository.save(order);
    }

    @Override
    public List<OrderItemDTO> getOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderItemDTO convertToDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
}