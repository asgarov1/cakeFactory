package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.entity.Product;
import com.asgarov.liveproject.cakefactory.repository.CatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CatalogServiceImplTest {

    @Autowired
    CatalogRepository catalogRepository;

    private CatalogService catalogService;

    @BeforeEach
    void setup() {
        this.catalogService = new CatalogServiceImpl(catalogRepository);
    }

    @Test
    @DisplayName("findAll() works as expected")
    void findAllWorksOk() {
        List<Product> products = catalogService.findAll();
        assertFalse(products.isEmpty());
    }

    @Test
    @DisplayName("findById() works as expected")
    void findByIdWorksOk() {
        Product croissant = Product.builder().title("Croissant").price(3.99).product_code("xyz").build();
        catalogRepository.save(croissant);

        Optional<Product> foundProduct = catalogRepository.findById(croissant.getProduct_code());
        assertTrue(foundProduct.isPresent());
        assertEquals(croissant, foundProduct.get());
    }


}