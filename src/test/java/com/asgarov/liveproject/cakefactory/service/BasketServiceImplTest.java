package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Basket;
import com.asgarov.liveproject.cakefactory.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BasketServiceImplTest {

    @Autowired
    BasketService basketService;

    @Autowired
    Basket basket;

    @Test
    @DisplayName("Several items to basket should all be correctly added")
    void addItemToBasket() {
        Item croissant = new Item("cr", "Croissant", 2.99);
        Item eclair = new Item("ec", "Eclair", 3.49);
        int numberOfCroissants = 2;
        int numberOfEclairs = 3;

        for (int i = 0; i < numberOfCroissants; i++) {
            basketService.addItemToBasket(croissant);
        }
        for (int i = 0; i < numberOfEclairs; i++) {
            basketService.addItemToBasket(eclair);
        }

        assertEquals(numberOfCroissants, basket.getItemsInBasket().get(croissant));
        assertEquals(numberOfEclairs, basket.getItemsInBasket().get(eclair));
    }

    @Test
    @DisplayName("Item count should diminish by one after first remove or disappear if count reaches zero")
    void removeItemFromBasket() {
        Item croissant = new Item("cr", "Croissant", 2.99);
        int numberOfCroissants = 2;

        basket.getItemsInBasket().put(croissant, numberOfCroissants);

        basketService.removeItemFromBasket(croissant);
        assertEquals(numberOfCroissants - 1, basket.getItemsInBasket().get(croissant));

        basketService.removeItemFromBasket(croissant);
        assertNull(basket.getItemsInBasket().get(croissant));
    }

    @Test
    @DisplayName("Clearing basket works as expected")
    void clearBasket() {
        Item croissant = new Item("cr", "Croissant", 2.99);
        int numberOfCroissants = 2;
        basket.getItemsInBasket().put(croissant, numberOfCroissants);
        assertFalse(basket.getItemsInBasket().isEmpty());

        basketService.clearBasket();
        assertTrue(basket.getItemsInBasket().isEmpty());
    }
}
