package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.Item;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogServiceImpl;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebClient webClient;

    @MockBean
    CatalogServiceImpl productService;

    @MockBean
    BasketService basketService;

    @Test
    @DisplayName("Index page returns the landing page")
    public void homeWorks() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("Cake Factory")));
    }

    @Test
    @DisplayName("Page title is ok")
    public void givenAClient_whenEnteringHomePage_thenPageTitleIsOk() throws Exception {
        String url = "http://localhost:8080/";
        HtmlPage page = webClient.getPage(url);
        assertEquals("Cake Factory Homepage", page.getTitleText());
    }

    @Test
    @DisplayName("Homepage displays all products")
    public void allProductsAreDisplayed() throws Exception {
        List<Item> items = Arrays.asList(
                new Item("abcr", "All Butter Croissant", 0.75),
                new Item("ccr", "Chocolate Croissant", 0.95),
                new Item("b", "Fresh Baguette", 1.60),
                new Item("rv", "Red Velvet", 3.95),
                new Item("vs", "Victoria Sponge", 5.45),
                new Item("cc", "Carrot Cake", 3.45));

        given(this.productService.findAll())
                .willReturn(items);

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(items.get(0).getTitle())))
                .andExpect(content().string(containsString(items.get(1).getTitle())))
                .andExpect(content().string(containsString(items.get(2).getTitle())))
                .andExpect(content().string(containsString(items.get(3).getTitle())))
                .andExpect(content().string(containsString(items.get(4).getTitle())))
                .andExpect(content().string(containsString(items.get(5).getTitle())));
    }
}