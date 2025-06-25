package com.shreyansh.store.payments;

import com.shreyansh.store.orders.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {

    private Long orderId;
    private PaymentStatus status;
}
