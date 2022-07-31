package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CoffeeMachineConstantValueRepositoryTest {

    @Autowired
    private CoffeeMachineConstantValueRepository underTest;

    @Test
    void getsCoffeeMachineConstantValueIfConstantNonExists() {
        String constantName = "Milk";
        CoffeeMachineConstantValue expected = underTest.findByName(constantName);
        Assertions.assertNull(expected);
    }

    @Test
    void getsCoffeeMachineConstantValueIfConstantExists() {
        //given
        String constantValueName = "constantValueTest";
        CoffeeMachineConstantValue coffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constantValueName, 1000);
        underTest.save(coffeeMachineConstantValueTest);
        //when
        CoffeeMachineConstantValue expected = underTest.findByName(constantValueName);
        //then
        assertThat(expected).isEqualTo(coffeeMachineConstantValueTest);
    }
}