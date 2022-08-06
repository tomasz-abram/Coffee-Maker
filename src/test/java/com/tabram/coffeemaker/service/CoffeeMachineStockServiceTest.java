package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoffeeMachineStockServiceTest {

    @Mock
    CoffeeMachineStockRepository coffeeMachineStockRepository;
    @Mock
    CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    private CoffeeMachineStockService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CoffeeMachineStockService(coffeeMachineStockRepository, coffeeMachineConstantValueService);
    }

    @Test
    void emptyWater() {
        CoffeeMachineStock waterStockTest = new CoffeeMachineStock("Water", 1000, "ml", "Ok");
        long expectedValue = 0;
        when(coffeeMachineStockRepository.findByName("Water")).thenReturn(waterStockTest);
        when(coffeeMachineConstantValueService.getMinWaterContainer()).thenReturn((int) expectedValue);
        underTest.emptyWater();

        ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
        verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
        CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

        assertEquals(expectedValue, capturedStock.getValue());

    }

    @Test
    void emptyMilk() {
        CoffeeMachineStock milkStockTest = new CoffeeMachineStock("Milk", 1000, "ml", "Ok");
        long expectedValue = 0;
        when(coffeeMachineStockRepository.findByName("Milk")).thenReturn(milkStockTest);
        when(coffeeMachineConstantValueService.getMinMilkContainer()).thenReturn((int) expectedValue);
        underTest.emptyMilk();

        ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
        verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
        CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

        assertEquals(expectedValue, capturedStock.getValue());
    }

    @Test
    void emptyCoffeeBeans() {
        CoffeeMachineStock coffeeBeansStockTest = new CoffeeMachineStock("Coffee beans", 200, "g", "Ok");
        long expectedValue = 0;
        when(coffeeMachineStockRepository.findByName("Coffee beans")).thenReturn(coffeeBeansStockTest);
        when(coffeeMachineConstantValueService.getMinCoffeeBeansContainer()).thenReturn((int) expectedValue);
        underTest.emptyCoffeeBeans();

        ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
        verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
        CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

        assertEquals(expectedValue, capturedStock.getValue());
    }

    @Test
    void emptyGroundContainer() {
        CoffeeMachineStock groundContainerStockTest = new CoffeeMachineStock("Ground container", 200, "g", "Ok");
        long expectedValue = 0;
        when(coffeeMachineStockRepository.findByName("Ground container")).thenReturn(groundContainerStockTest);

        underTest.emptyGroundContainer();

        ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
        verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
        CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
        assertEquals(expectedValue, capturedStock.getValue());
    }

    @Test
    void descale() {
        CoffeeMachineStock descaleStockTest = new CoffeeMachineStock("Ground container", 200, "g", "Ok");
        long expectedValue = 0;
        when(coffeeMachineStockRepository.findByName("Descale counter")).thenReturn(descaleStockTest);

        underTest.descale();

        ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
        verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
        CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
        assertEquals(expectedValue, capturedStock.getValue());
    }

    @Nested
    class AlarmLightStockStatusTest {
        @Test
        void itShouldGetTheStatusLightOk() {
            String expected = "Ok";
            CoffeeMachineStock waterStock = new CoffeeMachineStock("Water", 500, "ml", "Ok");
            CoffeeMachineStock milkStock = new CoffeeMachineStock("Milk", 300, "ml", "Ok");
            CoffeeMachineStock coffeeBeansStock = new CoffeeMachineStock("Coffee beans", 200, "g", "Ok");
            CoffeeMachineStock groundContainerStock = new CoffeeMachineStock("Ground container", 25, "pcs", "Ok");
            CoffeeMachineStock waterHardnessStock = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            CoffeeMachineStock descaleCounterStock = new CoffeeMachineStock("Descale counter", 2000, "", "Ok");
            List<CoffeeMachineStock> stocks = List.of(waterStock, milkStock, coffeeBeansStock, groundContainerStock, waterHardnessStock, descaleCounterStock);
            when(coffeeMachineStockRepository.findAll()).thenReturn(stocks);
            String actual = underTest.alarmLightStockStatus();

            assertThat(actual).isEqualTo(expected);
        }
        @Test
        void itShouldGetTheStatusLightWarning() {
            String expected = "Warning";
            CoffeeMachineStock waterStock = new CoffeeMachineStock("Water", 500, "ml", "Ok");
            CoffeeMachineStock milkStock = new CoffeeMachineStock("Milk", 300, "ml", "Ok");
            CoffeeMachineStock coffeeBeansStock = new CoffeeMachineStock("Coffee beans", 200, "g", "Warning");
            CoffeeMachineStock groundContainerStock = new CoffeeMachineStock("Ground container", 25, "pcs", "Ok");
            CoffeeMachineStock waterHardnessStock = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            CoffeeMachineStock descaleCounterStock = new CoffeeMachineStock("Descale counter", 2000, "", "Ok");
            List<CoffeeMachineStock> stocks = List.of(waterStock, milkStock, coffeeBeansStock, groundContainerStock, waterHardnessStock, descaleCounterStock);
            when(coffeeMachineStockRepository.findAll()).thenReturn(stocks);
            String actual = underTest.alarmLightStockStatus();

            assertThat(actual).isEqualTo(expected);
        }
        @Test
        void itShouldGetTheStatusLightDanger() {
            String expected = "Danger";
            CoffeeMachineStock waterStock = new CoffeeMachineStock("Water", 500, "ml", "Warning");
            CoffeeMachineStock milkStock = new CoffeeMachineStock("Milk", 300, "ml", "Ok");
            CoffeeMachineStock coffeeBeansStock = new CoffeeMachineStock("Coffee beans", 200, "g", "Warning");
            CoffeeMachineStock groundContainerStock = new CoffeeMachineStock("Ground container", 25, "pcs", "Ok");
            CoffeeMachineStock waterHardnessStock = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            CoffeeMachineStock descaleCounterStock = new CoffeeMachineStock("Descale counter", 2000, "", "Danger");
            List<CoffeeMachineStock> stocks = List.of(waterStock, milkStock, coffeeBeansStock, groundContainerStock, waterHardnessStock, descaleCounterStock);
            when(coffeeMachineStockRepository.findAll()).thenReturn(stocks);
            String actual = underTest.alarmLightStockStatus();

            assertThat(actual).isEqualTo(expected);
        }

    }

    @Nested
    class UpdateWaterHardnessTest {
        @Test
        void canUpdateWaterHardness() {
            CoffeeMachineStock waterHardnessStockTest = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            float valueHardnessTest = 20;
            when(coffeeMachineStockRepository.findByName("Water hardness")).thenReturn(waterHardnessStockTest);
            underTest.updateWaterHardness(valueHardnessTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertEquals(valueHardnessTest, capturedStock.getValue());

        }

        @Test
        void itCannotUpdateWaterHardnessTheGivenValueIsNegativeValue() {
            CoffeeMachineStock waterHardnessStockTest = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            float valueHardnessTest = -20;
            when(coffeeMachineStockRepository.findByName("Water hardness")).thenReturn(waterHardnessStockTest);

            assertThatThrownBy(
                    () -> underTest.updateWaterHardness(valueHardnessTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("The water hardness must not be less than zero");
        }

        @Test
        void itCannotUpdateWaterHardnessTheGivenValueIsUnrealValue() {
            CoffeeMachineStock waterHardnessStockTest = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "Ok");
            float valueHardnessTest = 110;
            when(coffeeMachineStockRepository.findByName("Water hardness")).thenReturn(waterHardnessStockTest);

            assertThatThrownBy(
                    () -> underTest.updateWaterHardness(valueHardnessTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("If you don't pour concrete over your coffee, this value is probably lower :)");
        }
    }

    @Nested
    class CheckStockStatusForWaterTest {
        @Test
        void itShouldGetTheStatusOkForWaterLevel() {
            String expected = "Ok";
            CoffeeMachineStock waterStockTest = new CoffeeMachineStock("Water", 600, "ml", "");
            CoffeeMachineConstantValue warningLevelWaterTest = new CoffeeMachineConstantValue("warning_level_water", 500);
            when(coffeeMachineStockRepository.findByName("Water")).thenReturn(waterStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelWater()).thenReturn(warningLevelWaterTest.getValue());

            underTest.checkStockStatusForWater();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusWarningForWaterLevel() {
            String expected = "Warning";
            CoffeeMachineStock waterStockTest = new CoffeeMachineStock("Water", 400, "ml", "");
            CoffeeMachineConstantValue warningLevelWaterTest = new CoffeeMachineConstantValue("warning_level_water", 500);
            when(coffeeMachineStockRepository.findByName("Water")).thenReturn(waterStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelWater()).thenReturn(warningLevelWaterTest.getValue());

            underTest.checkStockStatusForWater();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusDangerForWaterLevel() {
            String expected = "Danger";
            CoffeeMachineStock waterStockTest = new CoffeeMachineStock("Water", 100, "ml", "");
            CoffeeMachineConstantValue warningLevelWaterTest = new CoffeeMachineConstantValue("warning_level_water", 500);
            CoffeeMachineConstantValue dangerLevelWaterTest = new CoffeeMachineConstantValue("danger_level_water", 200);

            when(coffeeMachineStockRepository.findByName("Water")).thenReturn(waterStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelWater()).thenReturn(warningLevelWaterTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelWater()).thenReturn(dangerLevelWaterTest.getValue());

            underTest.checkStockStatusForWater();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }
    }

    @Nested
    class CheckStockStatusForMilkTest {
        @Test
        void canCheckStockStatusForMilk() {
            String expected = "Ok";
            CoffeeMachineStock milkStockTest = new CoffeeMachineStock("Milk", 600, "ml", "");
            CoffeeMachineConstantValue warningLevelMilkTest = new CoffeeMachineConstantValue("warning_level_milk", 500);
            when(coffeeMachineStockRepository.findByName("Milk")).thenReturn(milkStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelMilk()).thenReturn(warningLevelMilkTest.getValue());

            underTest.checkStockStatusForMilk();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusWarningForMilkLevel() {
            String expected = "Warning";
            CoffeeMachineStock milkStockTest = new CoffeeMachineStock("Milk", 300, "ml", "");
            CoffeeMachineConstantValue warningLevelMilkTest = new CoffeeMachineConstantValue("warning_level_milk", 500);
            when(coffeeMachineStockRepository.findByName("Milk")).thenReturn(milkStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelMilk()).thenReturn(warningLevelMilkTest.getValue());

            underTest.checkStockStatusForMilk();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusDangerForMilkLevel() {
            String expected = "Danger";
            CoffeeMachineStock milkStockTest = new CoffeeMachineStock("Milk", 100, "ml", "");
            CoffeeMachineConstantValue warningLevelMilkTest = new CoffeeMachineConstantValue("warning_level_milk", 500);
            CoffeeMachineConstantValue dangerLevelMilkTest = new CoffeeMachineConstantValue("danger_level_milk", 200);
            when(coffeeMachineStockRepository.findByName("Milk")).thenReturn(milkStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelMilk()).thenReturn(warningLevelMilkTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelMilk()).thenReturn(dangerLevelMilkTest.getValue());

            underTest.checkStockStatusForMilk();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }
    }

    @Nested
    class CheckStockStatusForCoffeeBeansTest {
        @Test
        void itShouldGetTheStatusOkForCoffeeBeansLevel() {
            String expected = "Ok";
            CoffeeMachineStock coffeeBeansStockTest = new CoffeeMachineStock("Coffee beans", 200, "g", "");
            CoffeeMachineConstantValue warningLevelCoffeeBeansTest = new CoffeeMachineConstantValue("warning_level_coffee_beans", 90);
            when(coffeeMachineStockRepository.findByName("Coffee beans")).thenReturn(coffeeBeansStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()).thenReturn(warningLevelCoffeeBeansTest.getValue());

            underTest.checkStockStatusForCoffeeBeans();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);

        }

        @Test
        void itShouldGetTheStatusWarningForCoffeeBeansLevel() {
            String expected = "Warning";
            CoffeeMachineStock coffeeBeansStockTest = new CoffeeMachineStock("Coffee beans", 80, "g", "");
            CoffeeMachineConstantValue warningLevelCoffeeBeansTest = new CoffeeMachineConstantValue("warning_level_coffee_beans", 90);
            CoffeeMachineConstantValue dangerLevelCoffeeBeansTest = new CoffeeMachineConstantValue("danger_level_coffee_beans", 20);
            when(coffeeMachineStockRepository.findByName("Coffee beans")).thenReturn(coffeeBeansStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()).thenReturn(warningLevelCoffeeBeansTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelCoffeeBeans()).thenReturn(dangerLevelCoffeeBeansTest.getValue());

            underTest.checkStockStatusForCoffeeBeans();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);

        }

        @Test
        void itShouldGetTheStatusDangerForCoffeeBeansLevel() {
            String expected = "Danger";
            CoffeeMachineStock coffeeBeansStockTest = new CoffeeMachineStock("Coffee beans", 10, "g", "");
            CoffeeMachineConstantValue warningLevelCoffeeBeansTest = new CoffeeMachineConstantValue("warning_level_coffee_beans", 90);
            CoffeeMachineConstantValue dangerLevelCoffeeBeansTest = new CoffeeMachineConstantValue("danger_level_coffee_beans", 20);
            when(coffeeMachineStockRepository.findByName("Coffee beans")).thenReturn(coffeeBeansStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()).thenReturn(warningLevelCoffeeBeansTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelCoffeeBeans()).thenReturn(dangerLevelCoffeeBeansTest.getValue());

            underTest.checkStockStatusForCoffeeBeans();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }
    }

    @Nested
    class CheckStockStatusForGroundContainerTest {
        @Test
        void itShouldGetTheStatusOkForGroundContainer() {
            String expected = "Ok";
            CoffeeMachineStock groundContainerStockTest = new CoffeeMachineStock("Ground container", 25, "pcs", "");
            CoffeeMachineConstantValue warningLevelGroundContainerTest = new CoffeeMachineConstantValue("warning_level_ground_container", 27);
            when(coffeeMachineStockRepository.findByName("Ground container")).thenReturn(groundContainerStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelGroundContainer()).thenReturn(warningLevelGroundContainerTest.getValue());

            underTest.checkStockStatusForGroundContainer();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusWarningForGroundContainer() {
            String expected = "Warning";
            CoffeeMachineStock groundContainerStockTest = new CoffeeMachineStock("Ground container", 28, "pcs", "");
            CoffeeMachineConstantValue warningLevelGroundContainerTest = new CoffeeMachineConstantValue("warning_level_ground_container", 27);
            CoffeeMachineConstantValue dangerLevelGroundContainerTest = new CoffeeMachineConstantValue("danger_level_ground_container", 30);
            when(coffeeMachineStockRepository.findByName("Ground container")).thenReturn(groundContainerStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelGroundContainer()).thenReturn(warningLevelGroundContainerTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelGroundContainer()).thenReturn(dangerLevelGroundContainerTest.getValue());

            underTest.checkStockStatusForGroundContainer();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusDangerForGroundContainer() {
            String expected = "Danger";
            CoffeeMachineStock groundContainerStockTest = new CoffeeMachineStock("Ground container", 30, "pcs", "");
            CoffeeMachineConstantValue warningLevelGroundContainerTest = new CoffeeMachineConstantValue("warning_level_ground_container", 27);
            CoffeeMachineConstantValue dangerLevelGroundContainerTest = new CoffeeMachineConstantValue("danger_level_ground_container", 30);
            when(coffeeMachineStockRepository.findByName("Ground container")).thenReturn(groundContainerStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelGroundContainer()).thenReturn(warningLevelGroundContainerTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelGroundContainer()).thenReturn(dangerLevelGroundContainerTest.getValue());

            underTest.checkStockStatusForGroundContainer();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }
    }

    @Nested
    class CheckStockStatusForDescaleCounterTest {
        @Test
        void itShouldGetTheStatusOkForDescaleCounter() {
            String expected = "Ok";
            CoffeeMachineStock descaleCounterStockTest = new CoffeeMachineStock("Descale counter", 2000, "", "");
            CoffeeMachineConstantValue warningLevelDescaleTest = new CoffeeMachineConstantValue("warning_level_descale", 5000);
            when(coffeeMachineStockRepository.findByName("Descale counter")).thenReturn(descaleCounterStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelDescale()).thenReturn(warningLevelDescaleTest.getValue());

            underTest.checkStockStatusForDescaleCounter();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusWarningForDescaleCounter() {
            String expected = "Warning";
            CoffeeMachineStock descaleCounterStockTest = new CoffeeMachineStock("Descale counter", 6000, "", "");
            CoffeeMachineConstantValue warningLevelDescaleTest = new CoffeeMachineConstantValue("warning_level_descale", 5000);
            CoffeeMachineConstantValue dangerLevelDescaleTest = new CoffeeMachineConstantValue("danger_level_descale", 10000);
            when(coffeeMachineStockRepository.findByName("Descale counter")).thenReturn(descaleCounterStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelDescale()).thenReturn(warningLevelDescaleTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelDescale()).thenReturn(dangerLevelDescaleTest.getValue());

            underTest.checkStockStatusForDescaleCounter();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }

        @Test
        void itShouldGetTheStatusDangerForDescaleCounter() {
            String expected = "Danger";
            CoffeeMachineStock descaleCounterStockTest = new CoffeeMachineStock("Descale counter", 11000, "", "");
            CoffeeMachineConstantValue warningLevelDescaleTest = new CoffeeMachineConstantValue("warning_level_descale", 5000);
            CoffeeMachineConstantValue dangerLevelDescaleTest = new CoffeeMachineConstantValue("danger_level_descale", 10000);
            when(coffeeMachineStockRepository.findByName("Descale counter")).thenReturn(descaleCounterStockTest);
            when(coffeeMachineConstantValueService.getWarningLevelDescale()).thenReturn(warningLevelDescaleTest.getValue());
            when(coffeeMachineConstantValueService.getDangerLevelDescale()).thenReturn(dangerLevelDescaleTest.getValue());

            underTest.checkStockStatusForDescaleCounter();

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();
            assertThat(capturedStock.getStatus()).isEqualTo(expected);
        }
    }


    @Nested
    class UpdateBeansTest {
        @Test
        void updatesTheCoffeeBeansQuantityIfItIsInRange() {
            int addBeansTest = 100;
            CoffeeMachineStock beansStockTest = new CoffeeMachineStock("Coffee beans", 100, "gr", "Ok");
            float expectedValue = addBeansTest + beansStockTest.getValue();
            when(coffeeMachineStockRepository.findByName("Coffee beans")).thenReturn(beansStockTest);
            when(coffeeMachineConstantValueService.getMaxCoffeeBeansContainer()).thenReturn(300);
            underTest.updateBeans(addBeansTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertEquals(expectedValue, capturedStock.getValue());
        }

        @Test
        void itUpdatesTheMilkWhenOutOfRange() {
            int addBeansTest = 5000;
            CoffeeMachineStock coffeeBeansStockTest = new CoffeeMachineStock("Coffee beans", 100, "gr", "Ok");
            float expectedValue = 300;
            when(coffeeMachineStockRepository.findByName("Coffee beans")).thenReturn(coffeeBeansStockTest);
            when(coffeeMachineConstantValueService.getMaxCoffeeBeansContainer()).thenReturn(300);
            underTest.updateBeans(addBeansTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertEquals(expectedValue, capturedStock.getValue());
        }


    }

    @Nested
    class UpdateMilkTest {
        @Test
        void updatesTheMilkQuantityIfItIsInRange() {
            int addMilkTest = 1000;
            CoffeeMachineStock milkStockTest = new CoffeeMachineStock("Milk", 1000, "ml", "Ok");
            float expectedValue = addMilkTest + milkStockTest.getValue();
            when(coffeeMachineStockRepository.findByName("Milk")).thenReturn(milkStockTest);
            when(coffeeMachineConstantValueService.getMaxMilkContainer()).thenReturn(3000);
            underTest.updateMilk(addMilkTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertEquals(expectedValue, capturedStock.getValue());
        }

        @Test
        void itUpdatesTheMilkWhenOutOfRange() {
            int addMilkTest = 5000;
            CoffeeMachineStock milkStockTest = new CoffeeMachineStock("Milk", 1000, "ml", "Ok");
            float expectedValue = 3000;
            when(coffeeMachineStockRepository.findByName("Milk")).thenReturn(milkStockTest);
            when(coffeeMachineConstantValueService.getMaxMilkContainer()).thenReturn(3000);
            underTest.updateMilk(addMilkTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertEquals(expectedValue, capturedStock.getValue());
        }
    }

    @Nested
    class UpdateWaterTest {
        @Test
        void updatesTheWaterQuantityIfItIsInRange() {
            int addWaterTest = 1000;
            CoffeeMachineStock waterStockTest = new CoffeeMachineStock("Water", 1000, "ml", "Ok");
            float expectedValue = addWaterTest + waterStockTest.getValue();
            when(coffeeMachineStockRepository.findByName("Water")).thenReturn(waterStockTest);
            when(coffeeMachineConstantValueService.getMaxWaterContainer()).thenReturn(3000);
            underTest.updateWater(addWaterTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertEquals(expectedValue, capturedStock.getValue());
        }

        @Test
        void itUpdatesTheWaterWhenOutOfRange() {
            int addWaterTest = 5000;
            CoffeeMachineStock waterStockTest = new CoffeeMachineStock("Water", 1000, "ml", "Ok");
            float expectedValue = 3000;
            when(coffeeMachineStockRepository.findByName("Water")).thenReturn(waterStockTest);
            when(coffeeMachineConstantValueService.getMaxWaterContainer()).thenReturn(3000);
            underTest.updateWater(addWaterTest);

            ArgumentCaptor<CoffeeMachineStock> stockArgumentCaptor = ArgumentCaptor.forClass(CoffeeMachineStock.class);
            verify(coffeeMachineStockRepository).save(stockArgumentCaptor.capture());
            CoffeeMachineStock capturedStock = stockArgumentCaptor.getValue();

            assertEquals(expectedValue, capturedStock.getValue());
        }
    }

    @Nested
    class FindStockByNameTest {
        @Test
        void canFindStockByName() {
            //given
            String stockName = "testStockName";
            //when
            underTest.findStockByName(stockName);
            //then
            verify(coffeeMachineStockRepository).findByName(stockName);
        }
    }
}