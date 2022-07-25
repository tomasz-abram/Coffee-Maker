package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CoffeeAdminServiceTest {
    @Mock
    private CoffeeAdminRepository coffeeAdminRepository;
    @Mock
    private CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    @Mock
    private UserRepository userRepository;
    private CoffeeAdminService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CoffeeAdminService(coffeeAdminRepository, coffeeMachineConstantValueService, userRepository);
    }

    @Nested
    class UpdateUser {
        @Test
        void canUpdateUser() {
            UserDto userDto = new UserDto();
            userDto.setUsername("UserTest");
            userDto.setEnabled(true);
            userDto.setRoles(Set.of(new Role("ROLE_TEST")));
            User userTest = new User("UserTest", "UserTest", true, Set.of(new Role("ROLE_USER")));
            when(userRepository.findByUserName(userDto.getUsername())).thenReturn(userTest);

            underTest.updateUser(userDto);

            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
            verify(userRepository).save(userArgumentCaptor.capture());
            User capturedUser = userArgumentCaptor.getValue();
            assertAll(
                    () -> assertEquals(userDto.getUsername(), capturedUser.getUsername()),
                    () -> assertEquals(userDto.getRoles(), capturedUser.getRoles()),
                    () -> assertEquals(userDto.isEnabled(), capturedUser.isEnabled()));
        }
    }

    @Nested
    class AddNewCoffee {
        @Test
        void canUpdateExistingCoffee() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffee", 95, 5, 17.5, 40, 0, 1, 60);
            CoffeeAdmin coffeeAdminTest = new CoffeeAdmin("TestCoffee", 95, 5, 17.5, 30, 100, 65, 200);
            when(coffeeAdminRepository.existsCoffeeAdminByCoffeeName(coffeeDtoTest.getCoffeeName())).thenReturn(true);
            when(coffeeAdminRepository.findCoffeeAdminByCoffeeName(coffeeDtoTest.getCoffeeName())).thenReturn(coffeeAdminTest);

            underTest.addNewCoffee(coffeeDtoTest);

            ArgumentCaptor<CoffeeAdmin> coffeeAdminArgumentCaptor = ArgumentCaptor.forClass(CoffeeAdmin.class);
            verify(coffeeAdminRepository).save(coffeeAdminArgumentCaptor.capture());
            CoffeeAdmin capturedCoffee = coffeeAdminArgumentCaptor.getValue();
            assertAll(

                    () -> assertEquals(coffeeDtoTest.getTempWater(), capturedCoffee.getTempWater()),
                    () -> assertEquals(coffeeDtoTest.getGrindingLevel(), capturedCoffee.getGrindingLevel()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfCoffee(), coffeeDtoTest.getAmountOfCoffee()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfWater(), coffeeDtoTest.getAmountOfWater()),
                    () -> assertEquals(coffeeDtoTest.getAmountMilk(), coffeeDtoTest.getAmountMilk()),
                    () -> assertEquals(coffeeDtoTest.getTempMilk(), coffeeDtoTest.getTempMilk()),
                    () -> assertEquals(coffeeDtoTest.getCupSize(), coffeeDtoTest.getCupSize()));
        }

        @Test
        void canAddNewCoffee() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 40, 0, 1, 60);

            underTest.addNewCoffee(coffeeDtoTest);

            ArgumentCaptor<CoffeeAdmin> coffeeAdminArgumentCaptor = ArgumentCaptor.forClass(CoffeeAdmin.class);
            verify(coffeeAdminRepository).save(coffeeAdminArgumentCaptor.capture());
            CoffeeAdmin capturedCoffee = coffeeAdminArgumentCaptor.getValue();
            assertAll(
                    () -> assertEquals(coffeeDtoTest.getCoffeeName(), capturedCoffee.getCoffeeName()),
                    () -> assertEquals(coffeeDtoTest.getTempWater(), capturedCoffee.getTempWater()),
                    () -> assertEquals(coffeeDtoTest.getGrindingLevel(), capturedCoffee.getGrindingLevel()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfCoffee(), coffeeDtoTest.getAmountOfCoffee()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfWater(), coffeeDtoTest.getAmountOfWater()),
                    () -> assertEquals(coffeeDtoTest.getAmountMilk(), coffeeDtoTest.getAmountMilk()),
                    () -> assertEquals(coffeeDtoTest.getTempMilk(), coffeeDtoTest.getTempMilk()),
                    () -> assertEquals(coffeeDtoTest.getCupSize(), coffeeDtoTest.getCupSize()));
        }

    }

    @Nested
    class GetAllCoffees {
        @Test
        void canGetAllCoffees() {
            underTest.getAllCoffees();
            verify(coffeeAdminRepository).findAll();
        }
    }

    @Nested
    class DeleteCoffee {
        @Test
        void canDeleteCoffee() {
            Long coffeeId = 10L;
            underTest.deleteCoffee(coffeeId);
            verify(coffeeAdminRepository).deleteById(coffeeId);
        }
    }

    @Nested
    class FindCoffeeById {
        @Test
        void canFindCoffeeById() {
            CoffeeAdmin coffeeTest = new CoffeeAdmin("CoffeeTest", 95, 5, 17.5, 40, 0, 0, 60);
            coffeeTest.setId(10L);
            // when
            when(coffeeAdminRepository.findById(coffeeTest.getId())).thenReturn(Optional.of(coffeeTest));

            underTest.findCoffeeById(coffeeTest.getId());
            //then
            verify(coffeeAdminRepository).findById(coffeeTest.getId());
        }

        @Test
        void cannotFindCoffeeByIdIfItDoesNotExist() {
            //given
            Long coffeeId = 10L;
            // when
            assertThatThrownBy(
                    () -> underTest.findCoffeeById(coffeeId))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Admin: coffee not found");

        }
    }

    @Nested
    class CheckCoffeeParameters {

//        @BeforeEach
//        public void setUp() {
//            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
//            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
//            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
//            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
//            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
//            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
//            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
//            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
//            when(coffeeMachineConstantValueService.getMinAmountOfMilk()).thenReturn(0);
//            when(coffeeMachineConstantValueService.getMaxAmountOfMilk()).thenReturn(500);
//            when(coffeeMachineConstantValueService.getMinTempMilk()).thenReturn(1);
//            when(coffeeMachineConstantValueService.getMaxTempMilk()).thenReturn(90);
//            when(coffeeMachineConstantValueService.getMinCupSize()).thenReturn(15);
//            when(coffeeMachineConstantValueService.getMaxCupSize()).thenReturn(1000);
//
//        }

        @Test
        void checkCoffeeParametersFoo() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 40, 0, 1, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinAmountOfMilk()).thenReturn(0);
            when(coffeeMachineConstantValueService.getMaxAmountOfMilk()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinTempMilk()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempMilk()).thenReturn(90);
            when(coffeeMachineConstantValueService.getMinCupSize()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxCupSize()).thenReturn(1000);
            underTest.checkCoffeeParameters(coffeeDtoTest);
        }

        @Test
        void willThrowExceptionWhenCoffeeNameDoesNotExists() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("", 95, 5, 17.5, 40, 0, 1, 60);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The name can not be empty.");
        }

        @Test
        void willThrowExceptionWhenWaterTemperatureIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 110, 5, 17.5, 40, 0, 1, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The water temperature must be in the range " + coffeeMachineConstantValueService.getMinTempWater() + " - " + coffeeMachineConstantValueService.getMaxTempWater());
        }

        @Test
        void willThrowExceptionWhenGrindingLevelIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 20, 17.5, 40, 0, 1, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The grinding level must be in the range " + coffeeMachineConstantValueService.getMinGrindingLevel() + " - " + coffeeMachineConstantValueService.getMaxGrindingLevel());
        }

        @Test
        void willThrowExceptionWhenAmountOfCoffeeIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 0, 40, 0, 1, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The amount of coffee must be in the range " + coffeeMachineConstantValueService.getMinAmountOfCoffee() + " - " + coffeeMachineConstantValueService.getMaxAmountOfCoffee());
        }

        @Test
        void willThrowExceptionWhenAmountOfWaterIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 600, 0, 1, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The amount of water must be in the range " + coffeeMachineConstantValueService.getMinAmountOfWater() + " - " + coffeeMachineConstantValueService.getMaxAmountOfWater());
        }

        @Test
        void willThrowExceptionWhenAmountOfMilkIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 40, 600, 1, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinAmountOfMilk()).thenReturn(0);
            when(coffeeMachineConstantValueService.getMaxAmountOfMilk()).thenReturn(500);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The amount milk must be in the range " + coffeeMachineConstantValueService.getMinAmountOfMilk() + " - " + coffeeMachineConstantValueService.getMaxAmountOfMilk());
        }

        @Test
        void willThrowExceptionWhenMilkTemperatureIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 40, 500, 100, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinAmountOfMilk()).thenReturn(0);
            when(coffeeMachineConstantValueService.getMaxAmountOfMilk()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinTempMilk()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempMilk()).thenReturn(90);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The temp. milk must be in the range " + coffeeMachineConstantValueService.getMinTempMilk() + " - " + coffeeMachineConstantValueService.getMaxTempMilk());
        }

        @Test
        void willThrowExceptionWhenCupSizeIsOutOfRange() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 40, 250, 50, 6000);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinAmountOfMilk()).thenReturn(0);
            when(coffeeMachineConstantValueService.getMaxAmountOfMilk()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinTempMilk()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempMilk()).thenReturn(90);
            when(coffeeMachineConstantValueService.getMinCupSize()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxCupSize()).thenReturn(1000);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The cup size must be in the range " + coffeeMachineConstantValueService.getMinCupSize() + " - " + coffeeMachineConstantValueService.getMaxCupSize());
        }

        @Test
        void willThrowExceptionWhenCupSizeIsToSmall() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("TestCoffeeDTO", 95, 5, 17.5, 40, 250, 50, 60);
            when(coffeeMachineConstantValueService.getMinTempWater()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempWater()).thenReturn(95);
            when(coffeeMachineConstantValueService.getMinGrindingLevel()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxGrindingLevel()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMinAmountOfCoffee()).thenReturn(10);
            when(coffeeMachineConstantValueService.getMaxAmountOfCoffee()).thenReturn(30);
            when(coffeeMachineConstantValueService.getMinAmountOfWater()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxAmountOfWater()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinAmountOfMilk()).thenReturn(0);
            when(coffeeMachineConstantValueService.getMaxAmountOfMilk()).thenReturn(500);
            when(coffeeMachineConstantValueService.getMinTempMilk()).thenReturn(1);
            when(coffeeMachineConstantValueService.getMaxTempMilk()).thenReturn(90);
            when(coffeeMachineConstantValueService.getMinCupSize()).thenReturn(15);
            when(coffeeMachineConstantValueService.getMaxCupSize()).thenReturn(1000);
            Assertions.assertThatThrownBy(() -> underTest.checkCoffeeParameters(coffeeDtoTest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("The cup must not be smaller than the sum of the amounts of water and milk.");
        }

    }
}