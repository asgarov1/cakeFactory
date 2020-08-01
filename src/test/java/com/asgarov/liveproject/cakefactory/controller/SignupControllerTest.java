package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignupController.class)
class SignupControllerTest {

    @Autowired
    MockMvc mockMvc;

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