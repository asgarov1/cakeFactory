package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Item;
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
    public List<Item> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Item findById(String id) {
        return catalogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
