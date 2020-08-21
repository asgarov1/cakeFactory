package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.OrderItem;

import java.util.List;

public interface CatalogService {

    List<OrderItem> findAll();

    OrderItem findById(String id);

}
