package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Value("${order.success}")
    String ORDER_SUCCESS_MESSAGE;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CatalogServiceImpl productService;

    @MockBean
    BasketService basketService;

    @Test
    @DisplayName("Posting the order works")
    public void postingOrderWorks() throws Exception {
        OrderDTO orderDTO = OrderDTO.builder()
                .firstName("Tom")
                .lastName("Smith")
                .email("tom.smith@gmail.com").build();

        String json = new Gson().toJson(orderDTO);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(ORDER_SUCCESS_MESSAGE)));
    }
}
