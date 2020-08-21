package com.asgarov.liveproject.cakefactory.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order extends AbstractPersistable<Long> {

    @ManyToMany
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime placedOrder;

    @OneToOne
    private Address address;

    public String getTotal() {
        return String.valueOf(orderItems.stream().mapToDouble(OrderItem::getPrice).sum());
    }
}
