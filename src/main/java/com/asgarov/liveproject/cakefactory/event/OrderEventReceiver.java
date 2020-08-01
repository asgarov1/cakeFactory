package com.asgarov.liveproject.cakefactory.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventReceiver {

    @EventListener
    public void onNewOrder(OrderEvent event) {
        log.info("==> Order Received: " + event.getOrderDTO());
        log.info("==> Ordered Items: ");
        event.getBasketItems().forEach(entry -> log.info("\t- " + entry.getKey() + ", " + entry.getValue() + " times"));
        log.info("====================");
    }
}
