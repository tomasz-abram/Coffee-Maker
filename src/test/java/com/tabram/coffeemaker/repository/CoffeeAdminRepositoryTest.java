package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CoffeeAdminRepositoryTest {
    @Autowired
    private CoffeeAdminRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void getsCoffeeCoffeeAdminRelationshipByCoffeeNameIfCoffeeExists() {
        String coffeeName = "CoffeeTest";
        CoffeeAdmin coffee = new CoffeeAdmin(
                coffeeName,
                95,
                5,
                17.5,
                40,
                120,
                65,
                200
        );
        underTest.save(coffee);
        CoffeeAdmin expected = underTest.findCoffeeAdminByCoffeeName(coffeeName);
        assertThat(expected).isEqualTo(coffee);
    }

    @Test
    void getsCoffeeCoffeeAdminRelationshipByCoffeeNameIfCoffeeNotExists() {
        String coffeeName = "CoffeeTest";
        CoffeeAdmin expected = underTest.findCoffeeAdminByCoffeeName(coffeeName);
        Assertions.assertNull(expected);
    }

    @Test
    void itShouldCheckIfCoffeeFromAdminExistsByCoffeeName() {
        //given
        String coffeeName = "CoffeeTest";
        CoffeeAdmin latteMacchiato = new CoffeeAdmin(
                coffeeName,
                95,
                5,
                17.5,
                40,
                120,
                65,
                200
        );
        underTest.save(latteMacchiato);
        //when
        boolean expected = underTest.existsCoffeeAdminByCoffeeName(coffeeName);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfCoffeeFromAdminDoesNotExistsByCoffeeName() {
        //given
        String coffeeName = "CoffeeTest";
        //when
        boolean expected = underTest.existsCoffeeAdminByCoffeeName(coffeeName);
        //then
        assertThat(expected).isFalse();
    }
}