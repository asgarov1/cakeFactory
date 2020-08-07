package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.CakefactoryApplication;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignupController.class)
@ContextConfiguration(classes = CakefactoryApplication.class)
@WithMockUser(username = "admin@admin.com", roles = "USER_ROLE")
class SignupControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Value("${signup.success}")
    private String SIGNUP_SUCCESS_MESSAGE;

    @Test
    void goToSignupPage() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(content().string(containsString("Registration Page")));
    }

    @Test
    void signup() throws Exception {
        SignupDTO signupDTO = SignupDTO.builder()
                .firstName("Tom")
                .lastName("Smith")
                .email("tom.smith@gmail.com")
                .password("password")
                .repeatPassword("password").build();

        String json = new Gson().toJson(signupDTO);
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(SIGNUP_SUCCESS_MESSAGE)));

    }
}