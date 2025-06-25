package com.shreyansh.store.orders;

import com.shreyansh.store.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentCustomer();
        var orders = orderRepository.getAllByCustomer(user);
        if(orders.isEmpty()) {
            throw new OrderNotFoundException();
        }
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrderById(Long orderId) {
        var order = orderRepository.getOrderWithItems(orderId).orElseThrow(OrderNotFoundException::new);
        var user = authService.getCurrentCustomer();
        if(!order.isPlacedBy(user)) {
            throw new AccessDeniedException("You do not have permission to access this order");
        }
        return orderMapper.toDto(order);
    }


}
