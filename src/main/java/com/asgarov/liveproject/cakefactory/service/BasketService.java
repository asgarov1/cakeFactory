package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.OrderItem;

import java.util.Map;
import java.util.Set;

public interface BasketService {
    void addItemToBasket(OrderItem orderItem);

    void removeItemFromBasket(OrderItem orderItem);

    void clearBasket();

    Integer countItems();

    Set<Map.Entry<OrderItem, Integer>> getBasketItems();

    Double getTotal();
}
