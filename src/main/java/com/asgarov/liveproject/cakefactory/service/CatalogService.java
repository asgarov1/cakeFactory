package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Item;

import java.util.List;

public interface CatalogService {

    List<Item> findAll();
    Item findById(String id);

}
