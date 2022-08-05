package com.tabram.coffeemaker.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MainController.class)
class MainControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainController underTest;

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content() .contentTypeCompatibleWith(MediaType.TEXT_HTML))
                ;
    }

    @Test
    void home() {
    }

    @Test
    void menu() {
    }
}