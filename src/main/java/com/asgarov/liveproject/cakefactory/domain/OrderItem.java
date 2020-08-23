package com.asgarov.liveproject.cakefactory.domain;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;

@Entity
@Table(name = "catalog")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItem {

    @Id
    private String itemCode;

    @Column
    private String title;

    @Column
    private double price;

    public String getImageName() {
        return title.replace(' ', '_').toLowerCase() + ".jpg";
    }
}


