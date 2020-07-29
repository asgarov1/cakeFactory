package com.asgarov.liveproject.cakefactory.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor
public class Basket {
    private final Map<Item, Integer> itemsInBasket = new HashMap<>();

    public void addItem(Item item) {
        itemsInBasket.computeIfPresent(item, (key, value) -> ++value);
        itemsInBasket.putIfAbsent(item, 1);
    }

    public void removeItem(Item item) {
        itemsInBasket.computeIfPresent(item, (key, value) -> --value);
        if(itemsInBasket.get(item) == 0) {
            itemsInBasket.remove(item);
        }
    }

    public void clear() {
        itemsInBasket.clear();
    }

    public Integer countItems() {
        return itemsInBasket.values().stream().mapToInt(Integer::valueOf).sum();
    }

    public Double getTotal() {
        return itemsInBasket.keySet().stream().mapToDouble(k -> k.getPrice() * itemsInBasket.get(k)).sum();
    }
}
