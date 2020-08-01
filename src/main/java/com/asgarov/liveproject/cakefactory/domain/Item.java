package com.asgarov.liveproject.cakefactory.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catalog")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {

    @Id
    private String itemCode;

    @Column
    private String title;

    @Column
    private double price;

}


