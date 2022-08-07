package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.service.CoffeeMachineStockService;
import com.tabram.coffeemaker.service.UserServiceInterface;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
class MainControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceInterface userServiceInterface;
    @MockBean
    CoffeeMachineStockService coffeeMachineStockService;

    @Nested
    class Login {
        @Test
        void itGoesToLoginPage() throws Exception {
            mockMvc.perform(get("/login"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Home {
        @Test
        void itGoesToHomePage() throws Exception {
            mockMvc.perform(get("/home"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }


        @Test
        void itGoesToHomePage2() throws Exception {
            mockMvc.perform(get("/"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Menu {
        @Test
        @WithMockUser
        void whenValidRole_ThenCheckStockStatus_AndReturnMav() throws Exception {
            when(coffeeMachineStockService.alarmLightStockStatus()).thenReturn("Ok");

            MvcResult mvcResult = mockMvc.perform(get("/menu")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals("Ok", modelAndView.getModel().get("light"));
            verify(coffeeMachineStockService, times(1)).checkStockStatus();
        }

        @Test
        void whenUnauthorizedUser_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/menu"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));
        }
    }
}