package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.event.OrderEvent;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/order")
@PropertySource("classpath:messages.properties")
public class OrderController {
    private final BasketService basketService;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${order.success}")
    private String ORDER_SUCCESS_MESSAGE;

    public OrderController(BasketService basketService, ApplicationEventPublisher eventPublisher) {
        this.basketService = basketService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processOrder(@RequestBody OrderDTO orderDTO) {
        eventPublisher.publishEvent(new OrderEvent(orderDTO, basketService.getBasketItems()));
        basketService.clearBasket();
        return ORDER_SUCCESS_MESSAGE;
    }
}
