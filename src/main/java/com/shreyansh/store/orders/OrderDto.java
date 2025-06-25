package com.shreyansh.store.orders;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private int id;
    private PaymentStatus status;
    private LocalDateTime orderDate;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
}
