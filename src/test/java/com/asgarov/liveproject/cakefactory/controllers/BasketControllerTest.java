package com.asgarov.liveproject.cakefactory.controllers;

import com.asgarov.liveproject.cakefactory.domain.Item;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasketController.class)
class BasketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BasketService basketService;

    @MockBean
    CatalogService catalogService;

    @Test
    @DisplayName("Add post request should be handler by the controller ok")
    public void test() throws Exception {
        mockMvc.perform(post("/basket?itemCode=abcr"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("A confirmation message should appear on the page")
    public void givenAClient_whenAddingItem_thenConfirmationMessageAppears() throws Exception {
        mockMvc.perform(get("/").flashAttr("numberOfItemsInBasket", "1")).andExpect(content().string(containsString("Basket: 1 item(s)")));
    }

    @Test
    @DisplayName("Basket should display added products")
    public void basketDisplaysProductsOk() throws Exception {
        Map<Item, Integer> basketItems = new HashMap<>();
        basketItems.put(new Item("abc", "Apple Biscuit", 2.39), 2);

        given(this.basketService.getBasketItems()).willReturn(basketItems.entrySet());
        given(this.basketService.getTotal()).willReturn(basketItems.keySet().stream().mapToDouble(k -> k.getPrice() * basketItems.get(k)).sum());

        mockMvc.perform(get("/basket"))
                .andExpect(content().string(containsString("Total:")))
                .andExpect(content().string(containsString("$" + basketService.getTotal())));
    }
}