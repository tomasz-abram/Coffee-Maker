package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CoffeeMachineStockRepositoryTest {

    @Autowired
    CoffeeMachineStockRepository underTest;


    @Test
    void getsStockIfStockNonExists() {
        String stockName = "TestStock";
        CoffeeMachineStock expected = underTest.findByName(stockName);
        Assertions.assertNull(expected);
    }

    @Test
    void getsStockIfStockExists() {
        //given
        String testStockName = "TestStock";
        CoffeeMachineStock CoffeeMachineStockTest = new CoffeeMachineStock(testStockName, 500, "ml", "");
        underTest.save(CoffeeMachineStockTest);
        //when
        CoffeeMachineStock expected = underTest.findByName(testStockName);
        //then
        assertThat(expected).isEqualTo(CoffeeMachineStockTest);
    }
}