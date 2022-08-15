package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.config.SecurityConfiguration;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.CoffeeMachineStockService;
import com.tabram.coffeemaker.service.UserServiceInterface;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CoffeeMachineStockController.class)
class CoffeeMachineStockControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CoffeeMachineStockService coffeeMachineStockService;
    @MockBean
    private CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    @Mock
    private SecurityConfiguration securityConfiguration;
    @MockBean
    private UserServiceInterface userServiceInterface;

    @Nested
    class GetStock {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void returnModelAndView() throws Exception {
            CoffeeMachineStock waterStock = new CoffeeMachineStock("Water", 500, "ml", "Ok");
            CoffeeMachineStock milkStock = new CoffeeMachineStock("Milk", 300, "ml", "Ok");
            CoffeeMachineStock coffeeBeansStock = new CoffeeMachineStock("Coffee beans", 200, "g", "Ok");
            CoffeeMachineStock groundContainerStock = new CoffeeMachineStock("Ground container", 25, "pcs", "Ok");
            CoffeeMachineStock waterHardnessStock = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            CoffeeMachineStock descaleCounterStock = new CoffeeMachineStock("Descale counter", 2000, "", "Ok");

            when(coffeeMachineStockService.findStockByName("Water")).thenReturn(waterStock);
            when(coffeeMachineStockService.findStockByName("Milk")).thenReturn(milkStock);
            when(coffeeMachineStockService.findStockByName("Coffee beans")).thenReturn(coffeeBeansStock);
            when(coffeeMachineStockService.findStockByName("Ground container")).thenReturn(groundContainerStock);
            when(coffeeMachineStockService.findStockByName("Descale counter")).thenReturn(descaleCounterStock);
            when(coffeeMachineStockService.findStockByName("Water hardness")).thenReturn(waterHardnessStock);

            MvcResult mvcResult = mockMvc.perform(get("/stock-coffee-machine"))
                    .andExpect(view().name("stock-coffee-machine"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(waterStock, modelAndView.getModel().get("waterStock"));
            assertEquals(milkStock, modelAndView.getModel().get("milkStock"));
            assertEquals(coffeeBeansStock, modelAndView.getModel().get("coffeeBeansStock"));
            assertEquals(groundContainerStock, modelAndView.getModel().get("groundContainerStock"));
            assertEquals(waterHardnessStock, modelAndView.getModel().get("waterHardnessStock"));
            assertEquals(descaleCounterStock, modelAndView.getModel().get("descaleCounter"));
            verify(coffeeMachineStockService, times(1)).checkStockStatus();
        }
    }

    @Nested
    class setWaterHardness {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenInputValid_ThenSetWaterHardness_AndRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/water-hardness")
                            .param("waterHardness", "10")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).updateWaterHardness(10);
        }
    }

    @Nested
    class FillWater {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenInputValid_ThenFillWater_AndRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/fill-water-tank")
                            .param("quantity", "10")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).updateWater(10);
        }
    }

    @Nested
    class EmptyWater {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenRemovesAllWaterFromTheContainer_ThenRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/empty-water-tank")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).emptyWater();
        }
    }

    @Nested
    class FillMilk {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenInputValid_ThenFillMilk_AndRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/fill-milk-tank")
                            .param("quantity", "100")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).updateMilk(100);
        }
    }

    @Nested
    class EmptyMilk {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenRemovesAllMilkFromTheContainer_ThenRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/empty-milk-tank")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).emptyMilk();
        }
    }

    @Nested
    class FillBeans {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenInputValid_ThenFillTheContainerWithCoffeeBeans_AndRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/fill-beans-container")
                            .param("quantity", "100")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).updateBeans(100);
        }
    }

    @Nested
    class EmptyCoffeeBeans {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenRemoveAllCoffeeBeansFromTheContainer_ThenRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/empty-coffee-beans")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).emptyCoffeeBeans();
        }
    }

    @Nested
    class CleanGroundContainer {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenCleansGroundContainer_ThenRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/empty-ground-container")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).emptyGroundContainer();
        }
    }

    @Nested
    class Descale {
        @Test
        @WithMockUser(roles = {"ADMIN", "USER"})
        void whenDescale_thenRedirect() throws Exception {
            mockMvc.perform(put("/stock-coffee-machine/descale")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/stock-coffee-machine"))
                    .andDo(print());

            verify(coffeeMachineStockService, times(1)).descale();
        }
    }
}