package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.RoleRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    private UserService underTest;


    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository, roleRepository);
    }

    @Nested
    class DeactivationUser {
        @Test
        void itShouldDeactivateTheUserIfExists() {
            //given
            User user = new User("UserTest", new BCryptPasswordEncoder().encode("UserTest"), true, Set.of(new Role("ROLE_USER")));
            user.setId(10L);
            //when
            given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
            underTest.deactivationUser(user.getId());
            //then
            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
            verify(userRepository).save(userArgumentCaptor.capture());
            User capturedUser = userArgumentCaptor.getValue();

            assertAll(
                    () -> assertEquals(user.getUsername(), capturedUser.getUsername()),
                    () -> assertEquals(user.getPassword(), capturedUser.getPassword()),
                    () -> assertEquals(user.isEnabled(), capturedUser.isEnabled()),
                    () -> assertEquals(user.getRoles(), capturedUser.getRoles()));
        }

        @Test
        void itShouldNotDeactivateTheUserIfUserDoesNotExists() {
            //given
            Long userId = 10L;
            //when
            assertThatThrownBy(
                    () -> underTest.deactivationUser(userId))
                    .isInstanceOf(UsernameNotFoundException.class)
                    .hasMessage("User with id " + userId + " does not exist");
            //then
            verify(userRepository, never()).save(any());
        }
    }

    @Nested
    class LoadUserByUsername {
        @Test
        void canLoadUserByUsername() {
            //given
            User user = new User("UserTest", new BCryptPasswordEncoder().encode("UserTest"), true, Set.of(new Role("ROLE_USER")));
            userRepository.save(user);

            given(userRepository.existsByUserName(user.getUsername())).willReturn(true);
            given(userRepository.findByUserName(user.getUsername())).willReturn(user);
            //when
            UserDetails userDetails = underTest.loadUserByUsername(user.getUsername());
            //then
            assertAll(
                    () -> assertEquals(userDetails.getUsername(), user.getUsername()),
                    () -> assertEquals(userDetails.getPassword(), user.getPassword()),
                    () -> assertEquals(userDetails.isEnabled(), user.isEnabled()),
                    () -> assertEquals(userDetails.getAuthorities().iterator().next().toString(), user.getRoles().iterator().next().getName()));
        }

        @Test
        void cannotLoadUserByUsername() {
            //given
            String username = "FakeTestUser";
            // when
            assertThatThrownBy(
                    () -> underTest.loadUserByUsername(username))
                    .isInstanceOf(UsernameNotFoundException.class)
                    .hasMessage("Invalid username or password.");

            verify(userRepository, never()).findByUserName(any());
        }
    }

    @Nested
    class CheckIfTheUserExists {
        @Test
        void itShouldCheckIfTheUserExists() {
            //given
            String username = "testUsername";
            //when
            underTest.checkIfTheUserExists(username);
            //then
            verify(userRepository).existsByUserName(username);
        }
    }

    @Nested
    class FindUserByName {
        @Test
        void canFindUserByUsernameIfItExists() {
            //given
            String username = "testUsername";
            //when
            underTest.findUserByName(username);
            //then
            verify(userRepository).findByUserName(username);
        }
    }

    @Nested
    class Save {
        @Test
        void saveUser() {
            //given
            UserRegistrationDto registrationDto = new UserRegistrationDto();
            registrationDto.setUserName("TestUsername");
            registrationDto.setPassword("TestPassword");
            Role role = new Role("ROLE_USER");
            roleRepository.save(role);
            given(roleRepository.findByName("ROLE_USER")).willReturn(role);
            //when
            underTest.save(registrationDto);
            //then
            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
            verify(userRepository).save(userArgumentCaptor.capture());
            User capturedUser = userArgumentCaptor.getValue();
            assertThat(capturedUser.getUsername()).isEqualTo(registrationDto.getUserName());
        }
    }

    @Nested
    class GetAllUserTest {
        @Test
        void canGetAllUsers() {
            //when
            underTest.getAllUsers();
            //then
            verify(userRepository).findAll();
        }
    }

    @Nested
    class FindUserByIdTest {

        @Test
        void cannotFindUserByIdIfItDoesNotExist() {
            //given
            long userId = 10;
            // when
            assertThatThrownBy(() -> underTest.findUserById(userId)).isInstanceOf(EntityNotFoundException.class).hasMessage("User not found");
        }

        @Test
        void canFindUserByIdIfUserExists() {
            //given
            User user = new User("UserTest", new BCryptPasswordEncoder().encode("UserTest"), true, Set.of(new Role("ROLE_USER")));
            user.setId(10L);
            // when
            when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
            underTest.findUserById(user.getId());
            //then
            verify(userRepository).findById(user.getId());
        }
    }

    @Nested
    class CurrentUserTest {
        @Test
        @WithMockUser(username = "testUsername")
        void canGiveTheCurrentUser() {
            //given
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            given(userRepository.existsByUserName(anyString())).willReturn(true);
            //when
            underTest.currentUser();
            //then
            verify(userRepository).findByUserName(authentication.getName());
        }

        @Test
        @WithMockUser(username = "testFalseUsername")
        void itCannotGiveTheCurrentUserIfItDoesNotExist() {
            //given
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            given(userRepository.existsByUserName(anyString())).willReturn(false);
            //when
            //then
            Assertions.assertThatThrownBy(() -> underTest.currentUser()).isInstanceOf(UsernameNotFoundException.class).hasMessageContaining("User not found");

            verify(userRepository, never()).findByUserName(authentication.getName());
        }
    }
}