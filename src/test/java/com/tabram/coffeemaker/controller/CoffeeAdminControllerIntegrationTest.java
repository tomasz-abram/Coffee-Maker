package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoffeeAdminController.class)
class CoffeeAdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CoffeeAdminService coffeeAdminService;
    @MockBean
    private CoffeeUserService coffeeUserService;
    @MockBean
    private CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    @MockBean
    private UserService userService;


    @Nested
    class ShowAddCoffeeForm {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValid_thenReturnMav() throws Exception {
            MvcResult mvcResult = mockMvc.perform(get("/admin/admin-add-coffee"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeMachineConstantValueService, modelAndView.getModel().get("coffeeMachine"));
        }
    }

    @Nested
    class AddCoffee {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenInputValid_thenVerifyMethods() throws Exception {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffee", 95, 5, 17.5, 40, 0, 1, 60);
            mockMvc.perform(post("/admin/admin-add-coffee")
                            .contentType(MediaType.TEXT_HTML)
                            .with(csrf())
                            .flashAttr("coffeeAdmin", coffeeDtoTest))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/admin/admin-coffee-list"));

            verify(coffeeAdminService, times(1)).checkCoffeeParameters(coffeeDtoTest);
            verify(coffeeAdminService, times(1)).addNewCoffee(coffeeDtoTest);
            ArgumentCaptor<CoffeeDto> coffeeDtoArgumentCaptor = ArgumentCaptor.forClass(CoffeeDto.class);
            verify(coffeeUserService, times(1)).addOneCoffeeForEachUser(coffeeDtoArgumentCaptor.capture());
            assertThat(coffeeDtoArgumentCaptor.getValue()).isEqualTo(coffeeDtoTest);
            verify(coffeeUserService, times(1)).updateDefaultCoffees(userService.findUserByName("Default"));
        }

    }

    @Nested
    class ShowUpdateForm {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenInputsValid_thenReturnCoffeeAdminAndCoffeeMachineMavObjects() throws Exception {
            CoffeeAdmin coffeeAdminTest = new CoffeeAdmin("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            when(coffeeAdminService.findCoffeeById(11L)).thenReturn(coffeeAdminTest);

            MvcResult mvcResult = mockMvc.perform(get("/admin/show-update-form")
                            .param("coffeeAdminId", "11"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("admin/admin-add-coffee"))
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeAdminTest, modelAndView.getModel().get("coffeeAdmin"));
            assertEquals(coffeeMachineConstantValueService, modelAndView.getModel().get("coffeeMachine"));
            verify(coffeeAdminService, times(1)).findCoffeeById(11L);
        }

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenNullValue_ThenReturn400() throws Exception {

            mockMvc.perform(get("/admin/show-update-form")
                            .param("coffeeAdminId", ""))
                    .andExpect(status().isBadRequest())
                    .andDo(print());

            verify(coffeeAdminService, never()).findCoffeeById(any());
        }
    }

    @Nested
    class GetAllCoffees {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidUrl_ThenReturnMavWithAllCoffees() throws Exception {
            CoffeeAdmin espresso = new CoffeeAdmin("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            CoffeeAdmin cappuccino = new CoffeeAdmin("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            List<CoffeeAdmin> coffeeAdminList = List.of(espresso, cappuccino);
            when(coffeeAdminService.getAllCoffees()).thenReturn(coffeeAdminList);

            MvcResult mvcResult = mockMvc.perform(get("/admin/admin-coffee-list"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeAdminList, modelAndView.getModel().get("coffees"));
            verify(coffeeAdminService, times(1)).getAllCoffees();
        }
    }

    @Nested
    class DeleteAdminCoffee {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidInput_ThenDeleteCoffee() throws Exception {
            mockMvc.perform(get("/admin/delete-adminCoffee")
                            .param("coffeeAdminId", "10"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/admin/admin-coffee-list"))
                    .andDo(print());
            verify(coffeeAdminService, times(1)).deleteCoffee(10L);
        }

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenNullValue_ThenReturn400() throws Exception {
            mockMvc.perform(get("/admin/delete-adminCoffee")
                            .param("coffeeAdminId", ""))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
            verify(coffeeAdminService, never()).deleteCoffee(any());
        }
    }
}