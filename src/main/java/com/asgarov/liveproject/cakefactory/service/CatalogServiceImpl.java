package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.OrderItem;
import com.asgarov.liveproject.cakefactory.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<OrderItem> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public OrderItem findById(String id) {
        return catalogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
