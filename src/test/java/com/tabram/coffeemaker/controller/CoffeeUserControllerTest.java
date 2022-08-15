package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
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
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoffeeUserController.class)
class CoffeeUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private CoffeeUserService coffeeUserService;
    @MockBean
    private CoffeeMachineConstantValueService coffeeMachineConstantValueService;


    @Nested
    class ShowAddCoffeeForm {
        @Test
        @WithMockUser(roles = "USER")
        void whenTheRoleIsCorrect_ThenReturnMav() throws Exception {
            MvcResult mvcResult = mockMvc.perform(get("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeMachineConstantValueService, modelAndView.getModel().get("coffeeMachine"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));
        }

        @Test
        @WithMockUser(roles = "DEFAULT")
        void whenWrongRole_ThenForwardedToRegistrationPage_Status403() throws Exception {
            mockMvc.perform(get("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(forwardedUrl("/registration?accessDenied"));
        }
    }

    @Nested
    class AddCoffee {

        @Test
        @WithMockUser(roles = {"USER", "ADMIN"})
        void whenTheRoleIsCorrect_ThenSaveNewCoffeeForCurrentUser() throws Exception {
            CoffeeDto coffeeDto = new CoffeeDto("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("ROLE_USER")));
            when(userService.currentUser()).thenReturn(userTest);

            mockMvc.perform(post("/user/user-add-coffee")
                            .contentType(MediaType.TEXT_HTML)
                            .with(csrf())
                            .flashAttr("coffeeUser", coffeeDto))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/coffee-settings"))
                    .andDo(print());

            ArgumentCaptor<CoffeeDto> coffeeDtoArgumentCaptor = ArgumentCaptor.forClass(CoffeeDto.class);
            verify(coffeeUserService, times(1)).saveCoffee(coffeeDtoArgumentCaptor.capture(), any());
            assertThat(coffeeDtoArgumentCaptor.getValue()).isEqualTo(coffeeDto);
        }

        @Test
        @WithMockUser(roles = "DEFAULT")
        void whenWrongRole_ThenForwardedToRegistrationPage_Status403() throws Exception {
            mockMvc.perform(post("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(forwardedUrl("/registration?accessDenied"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));
        }
    }

    @Nested
    class GetAllCoffee {
        @Test
        @WithMockUser()
        void whenTheRoleIsUser_ThenGetAllItCoffees() throws Exception {
            CoffeeUser espresso = new CoffeeUser("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            CoffeeUser cappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            List<CoffeeUser> coffeeUserList = List.of(espresso, cappuccino);
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("USER")), coffeeUserList);
            when(userService.currentUser()).thenReturn(userTest);
            when(userService.findUserByName(userTest.getUsername())).thenReturn(userTest);

            MvcResult mvcResult = mockMvc.perform(get("/coffee-settings")
                            .with(csrf()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeUserList, modelAndView.getModel().get("coffees"));
        }

        @Test
        @WithMockUser(roles = "DEFAULT")
        void whenTheRoleIsDefault_ThenGetAllBasicCoffees() throws Exception {
            CoffeeUser espresso = new CoffeeUser("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            CoffeeUser cappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            List<CoffeeUser> coffeeUserList = List.of(espresso, cappuccino);
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("USER")), coffeeUserList);
            when(userService.currentUser()).thenReturn(userTest);
            when(userService.findUserByName(userTest.getUsername())).thenReturn(userTest);

            MvcResult mvcResult = mockMvc.perform(get("/coffee-settings")
                            .with(csrf()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(coffeeUserList, modelAndView.getModel().get("coffees"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/coffee-settings"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));
        }
    }

    @Nested
    class ShowUpdateForm {
        @Test
        @WithMockUser(roles = "USER")
        void whenTheRoleIsUser_ThenReturnMav_AndShowUpdateForm() throws Exception {
            CoffeeUser espressoTest = new CoffeeUser("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            when(coffeeUserService.findCoffeeUserById(20L)).thenReturn(espressoTest);

            MvcResult mvcResult = mockMvc.perform(get("/user/show-update-form")
                            .param("coffeeUserId", "20"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(espressoTest, modelAndView.getModel().get("coffeeUser"));
            assertEquals(coffeeMachineConstantValueService, modelAndView.getModel().get("coffeeMachine"));
        }

        @Test
        @WithMockUser(roles = "DEFAULT")
        void whenWrongRole_ThenForwardedToRegistrationPage_Status403() throws Exception {
            mockMvc.perform(post("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(forwardedUrl("/registration?accessDenied"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/user/user-add-coffee"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));
        }
    }

    @Nested
    class DeleteUserCoffee {
        @Test
        @WithMockUser(roles = "USER")
        void whenRoleIsCorrect_ThenDeleteCoffee() throws Exception {
            mockMvc.perform(delete("/user/delete-user-coffee")
                            .param("coffeeUserId", "20")
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/coffee-settings"))
                    .andDo(print());

            verify(coffeeUserService, times(1)).deleteCoffee(20L);
        }

        @Test
        @WithMockUser(roles = "DEFAULT")
        void whenWrongRole_ThenForwardedToRegistrationPage_Status403() throws Exception {
            mockMvc.perform(post("/user/delete-user-coffee"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(forwardedUrl("/registration?accessDenied"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/user/delete-user-coffee"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));
        }
    }

    @Nested
    class UpdateCoffeeRecipes {
        @Test
        @WithMockUser(roles = "USER")
        void whenRoleIsCorrect_ThenUpdateCoffee() throws Exception {
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("ROLE_USER")));
            when(userService.currentUser()).thenReturn(userTest);

            mockMvc.perform(put("/user/update-coffee-recipes")
                    .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/coffee-settings"))
                    .andDo(print());

            verify(coffeeUserService, times(1)).updateDefaultCoffees(userTest);
        }

        @Test
        @WithMockUser(roles = "DEFAULT")
        void whenWrongRole_ThenForwardedToRegistrationPage_Status403() throws Exception {
            mockMvc.perform(post("/user/update-coffee-recipes"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(forwardedUrl("/registration?accessDenied"));
        }

        @Test
        void whenNotTheUserConnects_ThenRedirectToLoginPage() throws Exception {
            mockMvc.perform(get("/user/update-coffee-recipes"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("http://localhost/login"));

        }
    }
}