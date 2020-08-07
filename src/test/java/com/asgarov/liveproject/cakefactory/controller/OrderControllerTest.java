package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.CakefactoryApplication;
import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ContextConfiguration(classes = CakefactoryApplication.class)
@WithMockUser(username = "admin@admin.com", roles = "USER_ROLE")
class OrderControllerTest {

    @Value("${order.success}")
    String ORDER_SUCCESS_MESSAGE;

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @MockBean
    CatalogServiceImpl productService;

    @MockBean
    BasketService basketService;

    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

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
