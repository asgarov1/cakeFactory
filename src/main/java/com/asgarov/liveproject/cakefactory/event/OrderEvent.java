package com.asgarov.liveproject.cakefactory.event;

import com.asgarov.liveproject.cakefactory.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public class OrderEvent {
    private com.asgarov.liveproject.cakefactory.domain.Address Address;
    private Set<Map.Entry<OrderItem, Integer>> basketItems;
}
