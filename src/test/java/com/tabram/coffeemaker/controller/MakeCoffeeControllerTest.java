package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.MakeCoffeeService;
import com.tabram.coffeemaker.service.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MakeCoffeeController.class)
class MakeCoffeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private CoffeeUserService coffeeUserService;
    @MockBean
    private MakeCoffeeService makeCoffeeService;
    @MockBean
    private UserService userService;

    @Nested
    class GetAllCoffees {
        @Test
        @WithMockUser
        void whenTheRoleIsCorrect_ThenGetAllItCoffees() throws Exception {
            CoffeeUser espresso = new CoffeeUser("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            CoffeeUser cappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            List<CoffeeUser> coffeeUserList = List.of(espresso, cappuccino);
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("USER")), coffeeUserList);
            when(userService.currentUser()).thenReturn(userTest);
            when(userService.findUserByName(userTest.getUsername())).thenReturn(userTest);

            MvcResult mvcResult = mockMvc.perform(get("/make-coffee/list"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeUserList, modelAndView.getModel().get("coffees"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/make-coffee/list"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));

        }

    }

    @Nested
    class ChooseCoffee {
        @Test
        @WithMockUser
        void whenTheRoleIsCorrect_ThenReturnMavWithTheSelectedCoffee() throws Exception {
            CoffeeUser espresso = new CoffeeUser("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("USER")));
            userTest.setId(10L);
            when(userService.currentUser()).thenReturn(userTest);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(espresso.getCoffeeName(), userTest.getId())).thenReturn(espresso);
            when(coffeeUserService.tempCoffee(espresso)).thenReturn(70d);

            MvcResult mvcResult = mockMvc.perform(get("/make-coffee/{coffeeName}/strength", "Espresso"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(espresso, modelAndView.getModel().get("chooseCoffee"));
            assertEquals(70d, modelAndView.getModel().get("tempCoffee"));

        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/make-coffee/{coffeeName}/strength", "Espresso"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));

        }
    }

    @Nested
    class Brew {
        @Test
        @WithMockUser
        void whenTheRoleIsCorrect_ThenItMakesCoffee() throws Exception {
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("USER")));
            when(userService.currentUser()).thenReturn(userTest);

            mockMvc.perform(get("/make-coffee/{coffeeName}/{strength}/brew", "Espresso", "2")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/make-coffee/enjoy"))
                    .andDo(print())
                    .andReturn();

            verify(makeCoffeeService, times(1)).makeCoffee("Espresso", 2, userTest);
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/make-coffee/{coffeeName}/{strength}/brew", "Espresso", "2"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));

        }
    }
}