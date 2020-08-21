package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.Order;
import com.asgarov.liveproject.cakefactory.domain.OrderItem;
import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.domain.payment.PendingPayment;
import com.asgarov.liveproject.cakefactory.event.OrderEvent;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.paypal.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/order")
@PropertySource("classpath:messages.properties")
public class OrderController {
    private final BasketService basketService;
    private final ApplicationEventPublisher eventPublisher;
    private final PaymentService paymentService;
    private final ConcurrentHashMap<String, Address> pendingOrders = new ConcurrentHashMap<>();

    @Value("${order.success}")
    private String ORDER_SUCCESS_MESSAGE;

    public OrderController(BasketService basketService, ApplicationEventPublisher eventPublisher, PaymentService paymentService) {
        this.basketService = basketService;
        this.eventPublisher = eventPublisher;
        this.paymentService = paymentService;
    }

    @PostMapping
    public String processOrder(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String addressLine,
                               @RequestParam String country,
                               @RequestParam String city,
                               @RequestParam String zip, HttpServletRequest request) {
        Address address = Address.builder()
                .email(email)
                .addressLine(addressLine)
                .city(city)
                .country(country)
                .zip(zip).build();

        List<OrderItem> items = basketService.getBasketItems().stream().map(entry -> {
            List<OrderItem> temp = new ArrayList<>();
            for (int i = 0; i < entry.getValue() ; i++) {
                temp.add(entry.getKey());
            }
            return temp;
        }).flatMap(Collection::stream).collect(Collectors.toList());

        Order payment = Order.builder().address(address).orderItems(items).build();

        PendingPayment pendingPayment = paymentService.create(payment, buildReturnUrl(request));
        URI approveUri = pendingPayment.getApproveUri();
        pendingOrders.compute(pendingPayment.getId(), (s, o) -> address);

        return "redirect:" + approveUri;
    }

    @GetMapping("/complete")
    String completeOrder(@RequestParam String token, Model model) {
        try {
            paymentService.complete(token);
            this.eventPublisher.publishEvent(new OrderEvent(pendingOrders.getOrDefault(token, Address.builder().build()), basketService.getBasketItems()));
            basketService.clearBasket();
            this.pendingOrders.computeIfPresent(token, (s, o) -> null);
            return "redirect:/order/success";
        } catch (Exception e) {
            log.error("Failed to complete order", e);
            return "redirect:/";
        }
    }

    @GetMapping("/success")
    public String orderSuccessful(){
        return "success";
    }

    private URI buildReturnUrl(HttpServletRequest request) {
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(), requestUri.getUserInfo(), requestUri.getHost(), requestUri.getPort(), "/order/complete", null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
