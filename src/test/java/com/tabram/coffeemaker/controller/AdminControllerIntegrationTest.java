package com.tabram.coffeemaker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeMachineStockService;
import com.tabram.coffeemaker.service.RoleService;
import com.tabram.coffeemaker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)


@WebMvcTest(AdminController.class)
class AdminControllerIntegrationTest {


    @MockBean
    CoffeeAdminService coffeeAdminService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AdminController adminController;
    @MockBean
    private CoffeeMachineStockService coffeeMachineStockService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private UserService userService;

//    @Test
//    void userDto() {
//    }
//
//    @Test
//    void coffeeMachineConstantValueDto() {
//    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deactivationUser() throws Exception {
        mockMvc.perform(post("/admin/admin-users-list")
                        .with(csrf())
                        .param("userId", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllUsers() throws Exception {

        User testUser1 = new User("TestName1", "TestPassword1", true);
        User testUser2 = new User("TestName2", "TestPassword2", true);
        List<User> users = List.of(testUser1, testUser2);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/admin/admin-users-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(10L);
        userDto.setUsername("UserTest");
        userDto.setEnabled(true);
        userDto.setRoles(Set.of(new Role("ROLE_TEST")));

        mockMvc.perform(post("/admin/admin-update-user")
                        .contentType(MediaType.TEXT_HTML)
                        .param("id" , "10")
                        .param("username" , "TestUser")
                        .param("isEnabled" , "true")
                        .param("roles" , "ROLE_USER")
                        .characterEncoding("utf-8")
                        .with(csrf())
                        .flashAttr("userD", new UserDto()))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        ArgumentCaptor<UserDto> userDtoArgumentCaptor = ArgumentCaptor.forClass(UserDto.class);
        verify(coffeeAdminService, times(1)).updateUser(userDtoArgumentCaptor.capture());
        assertThat(userDtoArgumentCaptor.getValue().getUsername()).isEqualTo(userDto.getUsername());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void showUpdateForm() throws Exception {
        Role r1 = new Role("ROLE_ADMIN");
        Role r2 = new Role("ROLE_USER");
        Role r3 = new Role("ROLE_DEFAULT");
        List<Role> roles = List.of(r1, r2, r3);

        User user = new User("UserTest", "UserTest", true, Set.of(r2));

        when(userService.findUserById(10L)).thenReturn(user);
        when(roleService.getAllRoles()).thenReturn(roles);

        MvcResult mvcResult = mockMvc.perform(get("/admin/updateUserForm")
                        .param("userId", "10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();



    }

    @Test
    void coffeeMachineConstValue() {
    }

    @Test
    void addCoffee() {
    }

    @Test
    void updateConstForm() {
    }
}