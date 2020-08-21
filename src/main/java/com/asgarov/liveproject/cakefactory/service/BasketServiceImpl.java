package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Basket;
import com.asgarov.liveproject.cakefactory.domain.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class BasketServiceImpl implements BasketService {

    private final Basket basket;

    public BasketServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void addItemToBasket(OrderItem orderItem) {
        basket.addItem(orderItem);
    }

    @Override
    public void removeItemFromBasket(OrderItem orderItem) {
        basket.removeItem(orderItem);
    }

    @Override
    public void clearBasket() {
        basket.clear();
    }

    @Override
    public Integer countItems() {
        return basket.countItems();
    }

    @Override
    public Set<Map.Entry<OrderItem, Integer>> getBasketItems() {
        return basket.getItemsInBasket().entrySet();
    }

    @Override
    public Double getTotal() {
        return basket.getTotal();
    }
}
