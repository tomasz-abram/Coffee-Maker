package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CoffeeUserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CoffeeUserRepository underTest;

    @Test
    void getsCoffeeFromCoffeeUserRelationIfCoffeeExistsAndUserExists() {
        //Given
        User testUser = entityManager.persist(new User("UserNameTest", "Password", true));
        String testCoffeeName = "testCoffeeName";
        CoffeeUser testCoffeeUser = new CoffeeUser(
                testCoffeeName, 95, 5, 17.5, 40, 0, 0, 60, testUser
        );
        underTest.save(testCoffeeUser);
        //When
        CoffeeUser expected = underTest.findCoffeeUserByCoffeeNameAndUserId(testCoffeeName, testUser.getId());
        //Then
        assertEquals(expected, testCoffeeUser);
    }

    @Test
    void getsCoffeeFromCoffeeUserRelationIfCoffeeDoesNotExistAndUserExists() {
        //Given
        User testUser = entityManager.persist(new User("UserNameTest", "Password", true));
        String testCoffeeName = "testCoffeeName";
        //When
        CoffeeUser expected = underTest.findCoffeeUserByCoffeeNameAndUserId(testCoffeeName, testUser.getId());
        //Then
        assertThat(expected).isNull();
    }

    @Test
    void getsCoffeeFromCoffeeUserRelationIfCoffeeDoesNotExistAndUserDoesNotExist() {
        //Given
        Long testUserId = 99999L;
        String testCoffeeName = "testCoffeeName";
        //When
        CoffeeUser expected = underTest.findCoffeeUserByCoffeeNameAndUserId(testCoffeeName, testUserId);
        //Then
        assertThat(expected).isNull();
    }

    @Test
    void getsCoffeeFromCoffeeUserRelationIfCoffeeExistAndUserDoesNotExist() {
        //Given
        Long fakeUserId = 999999L;
        User testUser = entityManager.persist(new User("UserNameTest", "Password", true));
        String testCoffeeName = "testCoffeeName";
        CoffeeUser testCoffeeUser = new CoffeeUser(
                testCoffeeName, 95, 5, 17.5, 40, 0, 0, 60, testUser
        );
        underTest.save(testCoffeeUser);
        //When
        CoffeeUser expected = underTest.findCoffeeUserByCoffeeNameAndUserId(testCoffeeName, fakeUserId);
        //Then
        assertThat(expected).isNull();
    }


    @Test
    void itShouldCheckIfCoffeeFromCoffeeUserRelationExistsWhenCoffeeExistsAndUserExists() {
        //Given
        User testUser = entityManager.persist(new User("UserNameTest", "Password", true));
        String testCoffeeName = "testCoffeeName";
        CoffeeUser testCoffeeUser = new CoffeeUser(
                testCoffeeName, 95, 5, 17.5, 40, 0, 0, 60, testUser
        );
        underTest.save(testCoffeeUser);
        //When
        boolean expected = underTest.existsCoffeeUserByCoffeeNameAndUserId(testCoffeeName, testUser.getId());
        //Then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfCoffeeFromCoffeeUserRelationExistsWhenCoffeeDoesNotExistAndUserExists() {
        //Given
        User testUser = entityManager.persist(new User("UserNameTest", "Password", true));
        String testCoffeeName = "testCoffeeName";
        //When
        boolean expected = underTest.existsCoffeeUserByCoffeeNameAndUserId(testCoffeeName, testUser.getId());
        //Then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckIfCoffeeFromCoffeeUserRelationExistsWhenCoffeeDoesNotExistAndUserDoesNotExist() {
        //Given
        Long fakeUserId = 99999L;
        String testCoffeeName = "testCoffeeName";
        //When
        boolean expected = underTest.existsCoffeeUserByCoffeeNameAndUserId(testCoffeeName, fakeUserId);
        //Then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckIfCoffeeFromCoffeeUserRelationExistsWhenCoffeeExistAndUserDoesNotExist() {
        //Given
        Long fakeUserId = 999999L;
        User testUser = entityManager.persist(new User("UserNameTest", "Password", true));
        String testCoffeeName = "testCoffeeName";
        CoffeeUser testCoffeeUser = new CoffeeUser(
                testCoffeeName, 95, 5, 17.5, 40, 0, 0, 60, testUser
        );
        underTest.save(testCoffeeUser);
        //When
        boolean expected = underTest.existsCoffeeUserByCoffeeNameAndUserId(testCoffeeName, fakeUserId);
        //Then
        assertThat(expected).isFalse();
    }

}