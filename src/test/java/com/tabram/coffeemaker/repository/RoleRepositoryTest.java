package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void getsTheRoleByNameIfRoleExist() {
        //given
        String testName = "testRoleName";
        Role testRole = new Role(testName);
        underTest.save(testRole);
        //when
        Role expected = underTest.findByName(testName);
        //then
        assertEquals(expected, testRole);
    }

    @Test
    void getsTheRoleByNameIfRoleNotExist() {
        //given
        String testName = "testRoleName";
        //when
        Role expected = underTest.findByName(testName);
        //then
        assertThat(expected).isNull();
    }

    @Test
    void itShouldCheckIfRoleExists() {
        //given
        String testName = "testRoleName";
        Role testRole = new Role(testName);
        underTest.save(testRole);
        //when
        boolean expected = underTest.existsByName(testName);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfRoleDoesNotExists() {
        //given
        String testName = "testRoleName";
        //when
        boolean expected = underTest.existsByName(testName);
        //then
        assertThat(expected).isFalse();
    }
}