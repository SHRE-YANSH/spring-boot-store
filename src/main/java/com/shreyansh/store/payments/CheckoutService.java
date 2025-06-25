package com.shreyansh.store.payments;

import com.shreyansh.store.orders.Order;
import com.shreyansh.store.carts.CartEmptyException;
import com.shreyansh.store.carts.CartNotFoundException;
import com.shreyansh.store.carts.CartRepository;
import com.shreyansh.store.orders.OrderRepository;
import com.shreyansh.store.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {

        var cart = cartRepository.getCartsWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }
        var order = Order.fromCart(cart, authService.getCurrentCustomer());
        orderRepository.save(order);

        try {
            var session = paymentGateway.creatCheckoutSession(order);
            cart.clearCart();
            cartRepository.save(cart);
            return new CheckoutResponse(order.getId(), session.getCheckOutUrl());
        } catch (PaymentException e) {
            orderRepository.delete(order);
            throw e;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {

        paymentGateway.parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    order.setStatus(paymentResult.getStatus());
                    orderRepository.save(order);
                });


    }
}
