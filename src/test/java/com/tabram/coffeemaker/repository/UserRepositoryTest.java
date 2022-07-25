package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void getsUserIfUserExists() {

        String username = "TestUser";
        User user = new User(
                username,
                new BCryptPasswordEncoder().encode("TestUser"),
                true,
                Set.of(new Role("ROLE_USER")));

        underTest.save(user);

        User expected = underTest.findByUserName(username);

        assertThat(expected).isEqualTo(user);
    }

    @Test
    void getsUserIfUserNonExists() {

        String username = "UserTest";

        User expected = underTest.findByUserName(username);

        Assertions.assertNull(expected);
    }

    @Test
    void itShouldCheckIfUserExistsByUsername() {
        //given
        String username = "UserTest";
        User user = new User(
                username,
                "UserTest",
                true,
                Set.of(new Role("ROLE_USER")));

        underTest.save(user);
        //when
        boolean expected = underTest.existsByUserName(username);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfUserDoesNotExistsByUsername() {
        //given
        String username = "User";
        //when
        boolean expected = underTest.existsByUserName(username);
        //then
        assertThat(expected).isFalse();
    }
}