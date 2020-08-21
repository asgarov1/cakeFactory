package com.asgarov.liveproject.cakefactory.service.paypal;

import com.asgarov.liveproject.cakefactory.domain.Order;
import com.asgarov.liveproject.cakefactory.domain.payment.PendingPayment;

import java.net.URI;

public interface PaymentService {
    String DEFAULT_CURRENCY = "EUR";

    PendingPayment create(Order orderToPay, URI returnUri);
    String complete(String orderId);
}
