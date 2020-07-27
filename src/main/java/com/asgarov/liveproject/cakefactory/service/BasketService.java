package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Item;

public interface BasketService {
    void addItemToBasket(Item item);
    void removeItemFromBasket(Item item);
    void clearBasket();
    Integer countItems();
}
