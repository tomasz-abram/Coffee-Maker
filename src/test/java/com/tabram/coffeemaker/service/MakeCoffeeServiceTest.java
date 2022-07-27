package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MakeCoffeeServiceTest {

    @Mock
    CoffeeMachineStockRepository coffeeMachineStockRepository;
    @Mock
    CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    @Mock
    CoffeeMachineStockService coffeeMachineStockService;
    @Mock
    CoffeeUserService coffeeUserService;
    MakeCoffeeService underTest;

    @Captor
    private ArgumentCaptor<List<CoffeeMachineStock>> argCaptor;

    @BeforeEach
    void setUp() {
        underTest = new MakeCoffeeService(coffeeMachineStockRepository, coffeeMachineConstantValueService, coffeeMachineStockService, coffeeUserService);
    }

    @Nested
    class MakeCoffeeTest {
        @BeforeEach
        void setUp() {
            CoffeeMachineStock waterStock = new CoffeeMachineStock("Water", 500, "ml", "Warning");
            CoffeeMachineStock milkStock = new CoffeeMachineStock("Milk", 300, "ml", "Ok");
            CoffeeMachineStock coffeeBeansStock = new CoffeeMachineStock("Coffee beans", 100, "g", "Warning");
            CoffeeMachineStock groundContainerStock = new CoffeeMachineStock("Ground container", 28, "pcs", "Ok");
            CoffeeMachineStock descaleCounterStock = new CoffeeMachineStock("Descale counter", 2000, "", "Danger");
            CoffeeMachineStock waterHardnessStock = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");

            when(coffeeMachineStockService.findStockByName(waterStock.getName())).thenReturn(waterStock);
            when(coffeeMachineStockService.findStockByName(milkStock.getName())).thenReturn(milkStock);
            when(coffeeMachineStockService.findStockByName(coffeeBeansStock.getName())).thenReturn(coffeeBeansStock);
            when(coffeeMachineStockService.findStockByName(groundContainerStock.getName())).thenReturn(groundContainerStock);
            when(coffeeMachineStockService.findStockByName(descaleCounterStock.getName())).thenReturn(descaleCounterStock);
            when(coffeeMachineStockService.findStockByName(waterHardnessStock.getName())).thenReturn(waterHardnessStock);
        }

        @Test
        void itCanMakeCoffee() {

            int quantity = 1;
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(10L);
            CoffeeUser cappuccinoTest = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200, testUser);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(cappuccinoTest.getCoffeeName(), testUser.getId())).thenReturn(cappuccinoTest);
            when(coffeeMachineConstantValueService.getMaxGroundContainer()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMaxDescaleCounter()).thenReturn(3000);

            underTest.makeCoffee(cappuccinoTest.getCoffeeName(), quantity, testUser);

            verify(coffeeMachineStockRepository).saveAll(argCaptor.capture());
            List<CoffeeMachineStock> capturedListCoffee = argCaptor.getValue();
            assertThat(capturedListCoffee).hasSize(5);
            assertAll(
                    () -> assertEquals(500 - cappuccinoTest.getAmountOfWater(), capturedListCoffee.get(0).getValue()),
                    () -> assertEquals(300 - cappuccinoTest.getAmountMilk(), capturedListCoffee.get(1).getValue()),
                    () -> assertEquals(100 - cappuccinoTest.getAmountOfCoffee(), capturedListCoffee.get(2).getValue()),
                    () -> assertEquals(28 + quantity, capturedListCoffee.get(3).getValue()),
                    () -> assertEquals(2000 + 5 * cappuccinoTest.getAmountOfWater(), capturedListCoffee.get(4).getValue())
            );
        }

        @Test
        void itCanNotMakeCoffeeWhenNotEnoughWater() {
            int quantity = 1;
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(10L);
            String coffeeName = "Cappuccino";
            CoffeeUser cappuccinoTest = new CoffeeUser("Cappuccino", 95, 5, 17.5, 600, 100, 65, 200, testUser);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(cappuccinoTest.getCoffeeName(), testUser.getId())).thenReturn(cappuccinoTest);

            assertThatThrownBy(
                    () -> underTest.makeCoffee(coffeeName, quantity, testUser))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("There is not enough water in the machine to make this coffee");
        }

        @Test
        void itCanNotMakeCoffeeWhenNotEnoughMilk() {
            int quantity = 1;
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(10L);
            String coffeeName = "Cappuccino";
            CoffeeUser cappuccinoTest = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 500, 65, 200, testUser);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(cappuccinoTest.getCoffeeName(), testUser.getId())).thenReturn(cappuccinoTest);

            assertThatThrownBy(
                    () -> underTest.makeCoffee(coffeeName, quantity, testUser))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("There is not enough milk in the machine to make this coffee");
        }

        @Test
        void itCanNotMakeCoffeeWhenNotEnoughCoffeeBeans() {
            int quantity = 1;
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(10L);
            String coffeeName = "Cappuccino";
            CoffeeUser cappuccinoTest = new CoffeeUser("Cappuccino", 95, 5, 110, 30, 100, 65, 200, testUser);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(cappuccinoTest.getCoffeeName(), testUser.getId())).thenReturn(cappuccinoTest);

            assertThatThrownBy(
                    () -> underTest.makeCoffee(coffeeName, quantity, testUser))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("There is not enough coffee beans in the machine to make this coffee");
        }

        @Test
        void itCanNotMakeCoffeeWhenNotEnoughSpaceInGroundContainer() {
            int quantity = 3;
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(10L);
            String coffeeName = "Cappuccino";
            CoffeeUser cappuccinoTest = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200, testUser);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(cappuccinoTest.getCoffeeName(), testUser.getId())).thenReturn(cappuccinoTest);
            when(coffeeMachineConstantValueService.getMaxGroundContainer()).thenReturn(30);

            assertThatThrownBy(
                    () -> underTest.makeCoffee(coffeeName, quantity, testUser))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Empty the grounds container before making this coffee");
        }

        @Test
        void itCanNotMakeCoffeeWhenTheLimescaleLevelInTheInTheMachineIsToHigh() {
            int quantity = 1;
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(10L);
            String coffeeName = "Cappuccino";
            CoffeeUser cappuccinoTest = new CoffeeUser("Cappuccino", 95, 5, 17.5, 500, 0, 65, 1000, testUser);
            when(coffeeUserService.findCoffeeByCoffeeNameAndUserId(cappuccinoTest.getCoffeeName(), testUser.getId())).thenReturn(cappuccinoTest);
            when(coffeeMachineConstantValueService.getMaxGroundContainer()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMaxDescaleCounter()).thenReturn(2500);
            assertThatThrownBy(
                    () -> underTest.makeCoffee(coffeeName, quantity, testUser))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("Descale the coffee machine before making this coffee");
        }
    }
}