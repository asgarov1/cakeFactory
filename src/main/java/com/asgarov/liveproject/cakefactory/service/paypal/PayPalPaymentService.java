package com.asgarov.liveproject.cakefactory.service.paypal;

import com.asgarov.liveproject.cakefactory.domain.Order;
import com.asgarov.liveproject.cakefactory.domain.payment.PendingPayment;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PayPalPaymentService implements PaymentService{

    private final PayPalHttpClient payPalHttpClient;
    private final String APPROVE_LINK_REL = "approve";

    public PayPalPaymentService(PayPalEnvironment payPalEnvironment) {
        this.payPalHttpClient = new PayPalHttpClient(payPalEnvironment);
    }

    @SneakyThrows
    @Override
    public PendingPayment create(Order orderToPay, URI returnUri) {
        com.paypal.orders.Order order;
        OrderRequest orderRequest = new OrderRequest();

        orderRequest.checkoutPaymentIntent("CAPTURE");
        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();

        purchaseUnits.add(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode(PaymentService.DEFAULT_CURRENCY).value(orderToPay.getTotal())));
        orderRequest.purchaseUnits(purchaseUnits);
        OrdersCreateRequest createOrderRequest = new OrdersCreateRequest().requestBody(orderRequest);
        orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUri.toString()));

        HttpResponse<com.paypal.orders.Order> response = payPalHttpClient.execute(createOrderRequest);
        order = response.result();

        log.debug("Created order {}", order.id());

        LinkDescription approveUri = order.links()
                .stream()
                .filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst().orElseThrow();

        return new PendingPayment(order.id(), URI.create(approveUri.href()));
    }

    @Override
    @SneakyThrows
    public String complete(String orderId) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        HttpResponse<com.paypal.orders.Order> response = payPalHttpClient.execute(request);
        return response.result().status();
    }
}
