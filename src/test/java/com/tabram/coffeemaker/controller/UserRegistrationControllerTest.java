package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRegistrationController.class)
class UserRegistrationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private CoffeeUserService coffeeUserService;


    @Test
    void showRegistrationForm() throws Exception {
        mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Nested
    class RegisterUserAccount {
        @Test
        void whenTheUsernameIsNotTaken_thenSaveUser_AndAddBasicCoffeeToAccount_AndRedirectSuccess() throws Exception {
            UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
            userRegistrationDto.setUserName("TestUserDto");
            userRegistrationDto.setPassword("TestUserDtoPassword");
            User userTest = new User("TestUserDto", "TestUserDtoPassword", true, Set.of(new Role("ROLE_USER")));
            when(userService.checkIfTheUserExists("TestUserDto")).thenReturn(false);
            when(userService.findUserByName("TestUserDto")).thenReturn(userTest);

            mockMvc.perform(post("/registration")
                            .with(csrf())
                            .flashAttr("user", userRegistrationDto))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/login?regisSuccess"));

            verify(userService, times(1)).checkIfTheUserExists(userRegistrationDto.getUserName());
            verify(userService, times(1)).findUserByName("TestUserDto");
            verify(userService, times(1)).save(userRegistrationDto);

        }

        @Test
        void whenTheUsernameIsTaken_ThenRedirectToRegisError() throws Exception {
            UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
            userRegistrationDto.setUserName("TestUserDto");
            userRegistrationDto.setPassword("TestUserDtoPassword");
            when(userService.checkIfTheUserExists("TestUserDto")).thenReturn(true);

            mockMvc.perform(post("/registration")
                            .with(csrf())
                            .flashAttr("user", userRegistrationDto))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/registration?regisError"));

            verify(userService, times(1)).checkIfTheUserExists("TestUserDto");
            verify(userService, never()).findUserByName(any());
            verify(userService, never()).save(any());
        }
    }
}