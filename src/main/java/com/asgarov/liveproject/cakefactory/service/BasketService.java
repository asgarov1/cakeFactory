package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Item;

import java.util.Map;
import java.util.Set;

public interface BasketService {
    void addItemToBasket(Item item);

    void removeItemFromBasket(Item item);

    void clearBasket();

    Integer countItems();

    Set<Map.Entry<Item, Integer>> getBasketItems();

    Double getTotal();
}
