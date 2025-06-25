package com.shreyansh.store.orders;

import com.shreyansh.store.carts.Cart;
import com.shreyansh.store.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    public Order(User customer, PaymentStatus orderStatus, BigDecimal totalPrice) {
        this.customer = customer;
        this.status = orderStatus;
        this.totalPrice = totalPrice;


    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<OrderItem> items = new LinkedHashSet<>();

    public static Order fromCart(Cart cart, User user) {
        var order = new Order();
        order.setCustomer(user);
        order.setStatus(PaymentStatus.PENDING);
        order.setTotalPrice(cart.getTotalPrice());

        cart.getItems().forEach(item -> {
            var orderItem = new OrderItem(order, item.getProduct(), item.getProduct().getPrice(), item.getQuantity(), item.getTotalPrice());
            order.getItems().add(orderItem);
        });
        return order;
    }

    public boolean isPlacedBy(User customer) {
        return this.customer.equals(customer);
    }

}