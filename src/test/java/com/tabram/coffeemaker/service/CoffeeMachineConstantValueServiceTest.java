package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeMachineConstantValueDto;
import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import com.tabram.coffeemaker.repository.CoffeeMachineConstantValueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CoffeeMachineConstantValueServiceTest {

    @Mock
    private CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository;
    private CoffeeMachineConstantValueService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CoffeeMachineConstantValueService(coffeeMachineConstantValueRepository);
    }

    @Test
    void getMinWaterContainer() {
        String constName = "min_water_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinWaterContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxWaterContainer() {
        String constName = "max_water_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxWaterContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinMilkContainer() {
        String constName = "min_milk_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinMilkContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxMilkContainer() {
        String constName = "max_milk_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxMilkContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinCoffeeBeansContainer() {
        String constName = "min_coffee_beans_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinCoffeeBeansContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxCoffeeBeansContainer() {
        String constName = "max_coffee_beans_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxCoffeeBeansContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxGroundContainer() {
        String constName = "max_ground_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxGroundContainer();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxDescaleCounter() {
        String constName = "max_descale_counter";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxDescaleCounter();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxGrindingLevel() {
        String constName = "max_grinding_level";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxGrindingLevel();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinGrindingLevel() {
        String constName = "min_grinding_level";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinGrindingLevel();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxAmountOfCoffee() {
        String constName = "max_amount_of_coffee";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxAmountOfCoffee();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinAmountOfCoffee() {
        String constName = "min_amount_of_coffee";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinAmountOfCoffee();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxTempWater() {
        String constName = "max_temp_water";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxTempWater();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinTempWater() {
        String constName = "min_temp_water";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinTempWater();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxAmountOfWater() {
        String constName = "max_amount_of_water";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxAmountOfWater();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinAmountOfWater() {
        String constName = "min_amount_of_water";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinAmountOfWater();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxTempMilk() {
        String constName = "max_temp_milk";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxTempMilk();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinTempMilk() {
        String constName = "min_temp_milk";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinTempMilk();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxAmountOfMilk() {
        String constName = "max_amount_of_milk";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxAmountOfMilk();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinAmountOfMilk() {
        String constName = "min_amount_of_milk";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinAmountOfMilk();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMaxCupSize() {
        String constName = "max_cup_size";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMaxCupSize();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getMinCupSize() {
        String constName = "min_cup_size";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getMinCupSize();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getWarningLevelWater() {
        String constName = "warning_level_water";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getWarningLevelWater();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getDangerLevelWater() {
        String constName = "danger_level_water";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getDangerLevelWater();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getWarningLevelMilk() {
        String constName = "warning_level_milk";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getWarningLevelMilk();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getDangerLevelMilk() {
        String constName = "danger_level_milk";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getDangerLevelMilk();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getWarningLevelCoffeeBeans() {
        String constName = "warning_level_coffee_beans";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getWarningLevelCoffeeBeans();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getDangerLevelCoffeeBeans() {
        String constName = "danger_level_coffee_beans";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        underTest.getDangerLevelCoffeeBeans();
        assertThat(coffeeMachineConstantValueRepository.findByName(constName).getValue()).isEqualTo(CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getWarningLevelGroundContainer() {
        String constName = "warning_level_ground_container";
        String baseConstName = "max_ground_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        CoffeeMachineConstantValue cmcvBaseTest = new CoffeeMachineConstantValue(baseConstName, 100);

        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        given(coffeeMachineConstantValueRepository.findByName(baseConstName)).willReturn(cmcvBaseTest);

        underTest.getWarningLevelGroundContainer();

        Integer value = coffeeMachineConstantValueRepository.findByName(constName).getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName(baseConstName).getValue();
        assertThat(baseValue - value).isEqualTo(cmcvBaseTest.getValue() - CoffeeMachineConstantValueTest.getValue());

    }

    @Test
    void getDangerLevelGroundContainer() {
        String constName = "danger_level_ground_container";
        String baseConstName = "max_ground_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        CoffeeMachineConstantValue cmcvBaseTest = new CoffeeMachineConstantValue(baseConstName, 100);

        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        given(coffeeMachineConstantValueRepository.findByName(baseConstName)).willReturn(cmcvBaseTest);

        underTest.getDangerLevelGroundContainer();

        Integer value = coffeeMachineConstantValueRepository.findByName(constName).getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName(baseConstName).getValue();
        assertThat(baseValue - value).isEqualTo(cmcvBaseTest.getValue() - CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getWarningLevelDescale() {
        String constName = "warning_level_descale";
        String baseConstName = "max_descale_counter";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        CoffeeMachineConstantValue cmcvBaseTest = new CoffeeMachineConstantValue(baseConstName, 100);

        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        given(coffeeMachineConstantValueRepository.findByName(baseConstName)).willReturn(cmcvBaseTest);

        underTest.getWarningLevelDescale();

        Integer value = coffeeMachineConstantValueRepository.findByName(constName).getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName(baseConstName).getValue();
        assertThat(baseValue - value).isEqualTo(cmcvBaseTest.getValue() - CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void getDangerLevelDescale() {

        String constName = "danger_level_descale";
        String baseConstName = "max_ground_container";
        CoffeeMachineConstantValue CoffeeMachineConstantValueTest = new CoffeeMachineConstantValue(constName, 100);
        CoffeeMachineConstantValue cmcvBaseTest = new CoffeeMachineConstantValue(baseConstName, 100);

        given(coffeeMachineConstantValueRepository.findByName(constName)).willReturn(CoffeeMachineConstantValueTest);
        given(coffeeMachineConstantValueRepository.findByName(baseConstName)).willReturn(cmcvBaseTest);

        underTest.getDangerLevelDescale();

        Integer value = coffeeMachineConstantValueRepository.findByName(constName).getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName(baseConstName).getValue();
        assertThat(baseValue - value).isEqualTo(cmcvBaseTest.getValue() - CoffeeMachineConstantValueTest.getValue());
    }

    @Test
    void updateConstantValue() {
        //given
        CoffeeMachineConstantValueDto machineDto = new CoffeeMachineConstantValueDto();
        machineDto.setName("testConstantValue");
        machineDto.setValue(1230);

        CoffeeMachineConstantValue machineConstant = new CoffeeMachineConstantValue("testConstantValue", 4000);
        //when
        when(coffeeMachineConstantValueRepository.findByName(machineDto.getName())).thenReturn(machineConstant);
        underTest.updateConstantValue(machineDto);
        //then
        assertThat(machineConstant.getValue()).isEqualTo(machineDto.getValue());
    }

    @Test
    void getAllConstantValue() {
        underTest.getAllConstantValue();
        //then
        verify(coffeeMachineConstantValueRepository).findAll();
    }

    @Nested
    class FindConstantValueById {
        @Test
        void cannotFindConstantValueByIdIfItDoesNotExist() {
            Long constId = 10L;
            assertThatThrownBy(
                    () -> underTest.findConstantValueById(constId))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Constant value not found");
        }

        @Test
        void canFindConstantValueByIdIfConstantExists() {
            //given
            CoffeeMachineConstantValue machineConstant = new CoffeeMachineConstantValue("testConstantValue", 4000);
            machineConstant.setId(10L);
            //when
            when(coffeeMachineConstantValueRepository.findById(machineConstant.getId())).thenReturn(Optional.of(machineConstant));
            underTest.findConstantValueById(machineConstant.getId());
            //then
            verify(coffeeMachineConstantValueRepository).findById(machineConstant.getId());
        }
    }
}