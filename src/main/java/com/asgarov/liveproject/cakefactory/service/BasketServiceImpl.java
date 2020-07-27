package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Basket;
import com.asgarov.liveproject.cakefactory.domain.Item;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService{

    private final Basket basket;

    public BasketServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void addItemToBasket(Item item) {
        basket.addItem(item);
    }

    @Override
    public void removeItemFromBasket(Item item) {
        basket.removeItem(item);
    }

    @Override
    public void clearBasket() {
        basket.clear();
    }

    @Override
    public Integer countItems() {
        return basket.countItems();
    }
}
