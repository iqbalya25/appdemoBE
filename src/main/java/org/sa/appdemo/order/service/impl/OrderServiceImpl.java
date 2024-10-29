package org.sa.appdemo.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.sa.appdemo.exception.ResourceNotFoundException;
import org.sa.appdemo.order.dto.*;
import org.sa.appdemo.order.entity.Order;
import org.sa.appdemo.order.service.OrderService;
import org.sa.appdemo.orderitem.dto.OrderItemDTO;
import org.sa.appdemo.orderitem.entity.OrderItem;
import org.sa.appdemo.order.repository.OrderRepository;
import org.sa.appdemo.product.entity.Product;
import org.sa.appdemo.product.repository.ProductRepository;
import org.sa.appdemo.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setUser(userService.getCurrentAuthenticatedUser());
        order.setStatus("COMPLETED");
        order.setPaymentMethod("CASH");
        order.setPaymentStatus("PAID");

        // Process each item
        request.getItems().forEach(itemRequest -> {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(product.getPrice());
            item.calculateSubtotal();
            order.addItem(item);
        });

        order.recalculateTotal();

        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return convertToDTO(order);
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDiscountAmount(order.getDiscountAmount());
        dto.setFinalAmount(order.getFinalAmount());
        dto.setStatus(order.getStatus());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setPaymentStatus(order.getPaymentStatus());

        dto.setItems(order.getItems().stream()
                .map(this::convertToItemDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    private OrderItemDTO convertToItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
}