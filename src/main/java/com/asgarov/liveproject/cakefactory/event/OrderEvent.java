package com.asgarov.liveproject.cakefactory.event;

import com.asgarov.liveproject.cakefactory.domain.Item;
import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public class OrderEvent {
    private OrderDTO orderDTO;
    private Set<Map.Entry<Item, Integer>> basketItems;
}
