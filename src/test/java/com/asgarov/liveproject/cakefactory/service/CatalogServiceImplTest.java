package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.OrderItem;
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
        List<OrderItem> orderItems = catalogService.findAll();
        assertFalse(orderItems.isEmpty());
    }

    @Test
    @DisplayName("findById() works as expected")
    void findByIdWorksOk() {
        OrderItem croissant = OrderItem.builder().title("Croissant").price(3.99).itemCode("xyz").build();
        catalogRepository.save(croissant);

        Optional<OrderItem> foundProduct = catalogRepository.findById(croissant.getItemCode());
        assertTrue(foundProduct.isPresent());
        assertEquals(croissant, foundProduct.get());
    }
}