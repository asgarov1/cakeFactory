package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.CakefactoryApplication;
import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.service.AccountService;
import com.asgarov.liveproject.cakefactory.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = CakefactoryApplication.class)
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @MockBean
    AddressService addressService;

    @MockBean
    User user;

    @BeforeEach()
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)

                .apply(springSecurity())
                .build();
    }

    @Value("${update.success}")
    private String UPDATE_SUCCESS_MESSAGE;


    @Test
    @WithMockUser(username = "admin@admin.com", roles = "USER_ROLE")
    void account() throws Exception {
        mockMvc.perform(get("/account").flashAttr("email", "admin@admin.com")
                .with(user("admin").password("pass").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("account"));
    }

}