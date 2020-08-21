package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.CakefactoryApplication;
import com.asgarov.liveproject.cakefactory.domain.OrderItem;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasketController.class)
@ContextConfiguration(classes = CakefactoryApplication.class)
@WithMockUser(username = "admin@admin.com", roles = "USER_ROLE")
class BasketControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @MockBean
    BasketService basketService;

    @MockBean
    CatalogService catalogService;

    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Add post request should be handler by the controller ok")
    public void addItemWorks() throws Exception {
        mockMvc.perform(post("/basket?itemCode=abcr"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(basketService).addItemToBasket(catalogService.findById("abcr"));
    }

    @Test
    @DisplayName("A confirmation message should appear on the page")
    public void givenAClient_whenAddingItem_thenConfirmationMessageAppears() throws Exception {
        mockMvc.perform(get("/")
                .flashAttr("numberOfItemsInBasket", "1")
                .flashAttr("showBasket", true))
                .andExpect(content().string(containsString("Basket: 1 item(s)")));
    }

    @Test
    @DisplayName("Basket should display added products")
    public void viewBasketWorks() throws Exception {
        Map<OrderItem, Integer> basketItems = new HashMap<>();
        basketItems.put(new OrderItem("abc", "Apple Biscuit", 2.39), 2);

        given(this.basketService.getBasketItems()).willReturn(basketItems.entrySet());
        given(this.basketService.getTotal()).willReturn(basketItems.keySet().stream().mapToDouble(k -> k.getPrice() * basketItems.get(k)).sum());

        mockMvc.perform(get("/basket").with(csrf()))
                .andExpect(content().string(containsString("Total:")))
                .andExpect(content().string(containsString("$" + basketService.getTotal())));
    }

    @Test
    @DisplayName("Basket should remove products one at a time when pressed remove")
    public void removeWorks() throws Exception {
        String itemCode = "abc";
        mockMvc.perform(post("/basket/remove").param("itemCode", itemCode)
                .header("Accept","application/json"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/basket"));

        verify(basketService).removeItemFromBasket(catalogService.findById(itemCode));
    }
}