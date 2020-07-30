package com.asgarov.liveproject.cakefactory.controllers;

import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    private final BasketService basketService;

    public OrderController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String processOrder(@RequestBody OrderDTO orderDTO) {
        log.info("==> Order Received: " + orderDTO);
        log.info("==> Ordered Items: ");
        basketService.getBasketItems().forEach(entry -> log.info("\t- " + entry.getKey() + ", " + entry.getValue() + " times"));
        log.info("====================");
        basketService.clearBasket();
        return "Thank you, your order was placed successfully.";
    }
}
