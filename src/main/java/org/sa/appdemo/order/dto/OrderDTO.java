package org.sa.appdemo.order.dto;

import lombok.Data;
import org.sa.appdemo.orderitem.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private Long userId;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private String status;
    private String paymentMethod;
    private String paymentStatus;
    private List<OrderItemDTO> items = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}