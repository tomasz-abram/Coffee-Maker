package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.CoffeeMachineConstantValueDto;
import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.RoleService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private CoffeeAdminService coffeeAdminService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private CoffeeMachineConstantValueService coffeeMachineConstantValueService;


    @Nested
    class GetAllUsers {
        @Test
        @WithMockUser(roles = "ADMIN")
        void whenThereAreTwoUsers_ThenGetTwoUsers() throws Exception {
            User testUser1 = new User("TestName1", "TestPassword1", true);
            User testUser2 = new User("TestName2", "TestPassword2", true);
            List<User> users = List.of(testUser1, testUser2);
            when(userService.getAllUsers()).thenReturn(users);

            MvcResult mvcResult = mockMvc.perform(get("/admin/adminUsersList")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(users, modelAndView.getModel().get("users"));
        }
    }

    @Nested
    class UpdateUser {
        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidInput_ThenUpdateUser() throws Exception {
            UserDto userDto = new UserDto();
            userDto.setId(10L);
            userDto.setUsername("UserTest");
            userDto.setEnabled(true);
            userDto.setRoles(Set.of(new Role("ROLE_TEST")));

            mockMvc.perform(post("/admin/adminUpdateUser")
                            .contentType(MediaType.TEXT_HTML)
                            .with(csrf())
                            .flashAttr("userD", userDto))
                    .andDo(print())
                    .andExpect(redirectedUrl("/admin/adminUsersList"))
                    .andExpect(status().is3xxRedirection());

            ArgumentCaptor<UserDto> userDtoArgumentCaptor = ArgumentCaptor.forClass(UserDto.class);
            verify(coffeeAdminService, times(1)).updateUser(userDtoArgumentCaptor.capture());
            assertThat(userDtoArgumentCaptor.getValue().getUsername()).isEqualTo(userDto.getUsername());
        }
    }

    @Nested
    class ShowUpdateForm {
        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidInput_showUpdateForm() throws Exception {
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

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(modelAndView.getModel().get("roles"), roles);
            assertEquals(modelAndView.getModel().get("userD"), user);
        }
    }

    @Nested
    class CoffeeMachineConstValue {
        @Test
        @WithMockUser(roles = "ADMIN")
        void takesAListOfConstantsAndPassesToThePage() throws Exception {

            CoffeeMachineConstantValue coffeeMachineConstantValue1 = new CoffeeMachineConstantValue("testConstant1", 1000);
            CoffeeMachineConstantValue coffeeMachineConstantValue2 = new CoffeeMachineConstantValue("testConstant2", 2000);
            List<CoffeeMachineConstantValue> constValueList = List.of(coffeeMachineConstantValue1, coffeeMachineConstantValue2);
            when(coffeeMachineConstantValueService.getAllConstantValue()).thenReturn(constValueList);

            MvcResult mvcResult = mockMvc.perform(get("/admin/adminCoffeeMachineConstantValueList"))
                    .andExpect(status().isOk())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(modelAndView.getModel().get("constValues"), constValueList);
        }
    }

    @Nested
    class UpdateCoffeeMachineConstValue {
        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidInput_ThenUpdateValue() throws Exception {
            CoffeeMachineConstantValueDto coffeeMachineConstantValueDto = new CoffeeMachineConstantValueDto();
            coffeeMachineConstantValueDto.setName("testConstant");
            coffeeMachineConstantValueDto.setValue(1000);
            coffeeMachineConstantValueDto.setId(10L);

            mockMvc.perform(post("/admin/adminUpdateCoffeeMachineConstantValue")
                            .contentType(MediaType.TEXT_HTML)
                            .characterEncoding("UTF-8")
                            .flashAttr("machineConst", coffeeMachineConstantValueDto)
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(redirectedUrl("/admin/adminCoffeeMachineConstantValueList"))
                    .andExpect(status().is3xxRedirection());

            ArgumentCaptor<CoffeeMachineConstantValueDto> machineConstArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineConstantValueDto.class);
            verify(coffeeMachineConstantValueService, times(1)).updateConstantValue(machineConstArgumentCaptor.capture());
            assertThat(machineConstArgumentCaptor.getValue().getValue()).isEqualTo(1000);
        }
    }

    @Nested
    class UpdateConstForm {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidInput_ITGoesToUpdatePage() throws Exception {
            CoffeeMachineConstantValue coffeeMachineConstantValue = new CoffeeMachineConstantValue("TestConstant", 8888);
            when(coffeeMachineConstantValueService.findConstantValueById(10L)).thenReturn(coffeeMachineConstantValue);
            MvcResult mvcResult = mockMvc.perform(get("/admin/updateConstForm")
                            .contentType(MediaType.TEXT_HTML)
                            .with(csrf())
                            .param("machineConstId", "10"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("admin/adminUpdateCoffeeMachineConstantValue"))
                    .andDo(print())
                    .andReturn();

            ModelAndView modelAndView = mvcResult.getModelAndView();
            assertEquals(modelAndView.getModel().get("machineConst"), coffeeMachineConstantValue);
        }
    }

    @Nested
    class DeactivationUser {
        @Test
        @WithMockUser(roles = "ADMIN")
        void whenValidInput_ThenItDeactivatesTheUser() throws Exception {
            mockMvc.perform(post("/admin/deactivateUser")
                            .contentType(MediaType.TEXT_HTML)
                            .param("userId", "10")
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(redirectedUrl("/admin/adminUsersList"))
                    .andExpect(status().is3xxRedirection());
            verify(userService, times(1)).deactivationUser(10L);
        }
    }

}