package com.shreyansh.store.payments;

import com.shreyansh.store.orders.Order;

import java.util.Optional;

public interface PaymentGateway {

    CheckoutSession creatCheckoutSession(Order order);
    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
