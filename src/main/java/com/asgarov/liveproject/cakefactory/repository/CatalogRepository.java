package com.asgarov.liveproject.cakefactory.repository;

import com.asgarov.liveproject.cakefactory.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends CrudRepository<Product, String> {
    List<Product> findAll();
    Optional<Product> findById(String id);
}
