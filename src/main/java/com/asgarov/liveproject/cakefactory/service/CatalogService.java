package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CatalogService {

    List<Product> findAll();
    Product findById(String id);

}
