package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoffeeUserServiceTest {

    CoffeeUserService underTest;
    @Mock
    private CoffeeUserRepository coffeeUserRepository;
    @Mock
    private CoffeeAdminRepository coffeeAdminRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CoffeeAdminService coffeeAdminService;
    @Mock
    private UserService userService;
    @Captor
    private ArgumentCaptor<List<CoffeeUser>> argCaptor;

    @BeforeEach
    void setUp() {
        underTest = new CoffeeUserService(coffeeUserRepository, coffeeAdminRepository, userRepository, coffeeAdminService, userService);
    }


    @Nested
    class FindCoffeeByCoffeeNameAndUserId {
        @Test
        void canFindCoffeeByCoffeeNameAndUserId() {
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(8L);
            CoffeeUser testCappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200, testUser);
            when(coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId(testCappuccino.getCoffeeName(), testUser.getId())).thenReturn(testCappuccino);
            CoffeeUser actual = underTest.findCoffeeByCoffeeNameAndUserId(testCappuccino.getCoffeeName(), testUser.getId());
            assertThat(actual).isEqualTo(testCappuccino);
        }
    }

    @Nested
    class DeleteCoffeeTest {
        @Test
        void itRemovesTheUsersCoffee() {
            Long testCoffeeId = 10L;
            underTest.deleteCoffee(testCoffeeId);
            verify(coffeeUserRepository).deleteById(testCoffeeId);
        }
    }

    @Nested
    class AddCoffeeListToUserTest {
        @Test
        void itAddsTheEntireCoffeeListPreparedByAdmin() {
            User testUser = new User("TestName", "TestPassword", true);
            testUser.setId(8L);
            CoffeeAdmin testAdminCappuccino = new CoffeeAdmin("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            CoffeeAdmin testAdminMacchiato = new CoffeeAdmin("Macchiato", 95, 5, 17.5, 40, 10, 65, 100);
            List<CoffeeAdmin> testListCoffeeAdmin = List.of(testAdminCappuccino, testAdminMacchiato);
            CoffeeUser testUserCappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200, testUser);
            CoffeeUser testUserMacchiato = new CoffeeUser("Macchiato", 95, 5, 17.5, 40, 10, 65, 100, testUser);
            List<CoffeeUser> expectedListCoffeeUser = List.of(testUserCappuccino, testUserMacchiato);
            when(coffeeAdminRepository.findAll()).thenReturn(testListCoffeeAdmin);

            underTest.addCoffeeListToUser(testUser);

            verify(coffeeUserRepository).saveAll(argCaptor.capture());
            List<CoffeeUser> capturedListCoffee = argCaptor.getValue();
            CoffeeUser capCoffee = capturedListCoffee.get(0);

            assertAll(
                    () -> assertEquals(testUserCappuccino.getCoffeeName(), capCoffee.getCoffeeName()),
                    () -> assertEquals(testUserCappuccino.getTempWater(), capCoffee.getTempWater()),
                    () -> assertEquals(testUserCappuccino.getGrindingLevel(), capCoffee.getGrindingLevel()),
                    () -> assertEquals(testUserCappuccino.getAmountOfCoffee(), capCoffee.getAmountOfCoffee()),
                    () -> assertEquals(testUserCappuccino.getAmountOfWater(), capCoffee.getAmountOfWater()),
                    () -> assertEquals(testUserCappuccino.getAmountMilk(), capCoffee.getAmountMilk()),
                    () -> assertEquals(testUserCappuccino.getTempMilk(), capCoffee.getTempMilk()),
                    () -> assertEquals(testUserCappuccino.getCupSize(), capCoffee.getCupSize()),
                    () -> assertEquals(testUserCappuccino.getUser(), capCoffee.getUser())
            );
            assertThat(capturedListCoffee).hasSameSizeAs(expectedListCoffeeUser);
        }
    }

    @Nested
    class UpdateDefaultCoffeesTest {
        @Test
        void canCompereCoffeesOfAdminsAndUsersAndAddMissingCoffeesToTheUserOrUpdateRecipeOfTheExists() {
            User testUser1 = new User("TestName1", "TestPassword", true);
            testUser1.setId(10L);
            CoffeeUser userEspressoTest = new CoffeeUser("Espresso", 80, 4, 20, 500, 10, 5, 1000, testUser1);
            CoffeeAdmin espressoTest = new CoffeeAdmin("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            CoffeeAdmin cappuccinoTest = new CoffeeAdmin("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            CoffeeAdmin latteMacchiatoTest = new CoffeeAdmin("LatteMacchiato", 95, 5, 17.5, 40, 120, 65, 200);
            List<CoffeeAdmin> coffeesTest = List.of(espressoTest, cappuccinoTest, latteMacchiatoTest);
            when(coffeeAdminRepository.findAll()).thenReturn(coffeesTest);
            when(coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(espressoTest.getCoffeeName(), testUser1.getId())).thenReturn(true);
            when(coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId(espressoTest.getCoffeeName(), testUser1.getId())).thenReturn(userEspressoTest);
            underTest.updateDefaultCoffees(testUser1);
            verify(coffeeUserRepository).saveAll(argCaptor.capture());
            List<CoffeeUser> capturedListCoffee = argCaptor.getValue();
            CoffeeUser capCoffee = capturedListCoffee.get(0);

            assertThat(capturedListCoffee).hasSize(3);
            assertAll(
                    () -> assertEquals(espressoTest.getCoffeeName(), capCoffee.getCoffeeName()),
                    () -> assertEquals(espressoTest.getTempWater(), capCoffee.getTempWater()),
                    () -> assertEquals(espressoTest.getGrindingLevel(), capCoffee.getGrindingLevel()),
                    () -> assertEquals(espressoTest.getAmountOfCoffee(), capCoffee.getAmountOfCoffee()),
                    () -> assertEquals(espressoTest.getAmountOfWater(), capCoffee.getAmountOfWater()),
                    () -> assertEquals(espressoTest.getAmountMilk(), capCoffee.getAmountMilk()),
                    () -> assertEquals(espressoTest.getTempMilk(), capCoffee.getTempMilk()),
                    () -> assertEquals(espressoTest.getCupSize(), capCoffee.getCupSize()),
                    () -> assertEquals(testUser1, capCoffee.getUser()),
                    () -> assertEquals(testUser1, capturedListCoffee.get(1).getUser()),
                    () -> assertEquals(testUser1, capturedListCoffee.get(2).getUser())

            );
        }
    }

    @Nested
    class TempCoffeeTest {
        @Test
        void canCalculateTheTemperatureOfTheCoffee() {
            User testUser = new User("TestName1", "TestPassword", true);
            CoffeeUser testUserCappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200, testUser);
            double expected = 71.92;
            Double actual = underTest.tempCoffee(testUserCappuccino);
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class AddOneCoffeeForEachUserTest {
        @Test
        void canAddCoffeeWhenCoffeeDoesNotExists() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("Ristretto", 95, 5, 17.5, 20, 0, 0, 60);
            User testUser1 = new User("TestName1", "TestPassword", true);
            testUser1.setId(1L);
            User testUser2 = new User("TestName2", "TestPassword", true);
            testUser2.setId(2L);
            List<User> userList = List.of(testUser1, testUser2);
            when(userRepository.findAll()).thenReturn(userList);

            underTest.addOneCoffeeForEachUser(coffeeDtoTest);

            verify(coffeeUserRepository).saveAll(argCaptor.capture());
            List<CoffeeUser> capturedListCoffee = argCaptor.getValue();
            assertThat(capturedListCoffee).hasSameSizeAs(userList);
        }

        @Test
        void cannotAddCoffeeWhenCoffeeAlreadyExists() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("Ristretto", 95, 5, 17.5, 20, 0, 0, 60);
            User testUser1 = new User("TestName1", "TestPassword", true);
            testUser1.setId(1L);
            User testUser2 = new User("TestName2", "TestPassword", true);
            testUser2.setId(2L);
            when(coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(anyString(), anyLong())).thenReturn(true);
            List<User> userList = List.of(testUser1, testUser2);
            when(userRepository.findAll()).thenReturn(userList);

            underTest.addOneCoffeeForEachUser(coffeeDtoTest);

            verify(coffeeUserRepository).saveAll(argCaptor.capture());
            List<CoffeeUser> capturedListCoffee = argCaptor.getValue();
            assertThat(capturedListCoffee).isEmpty();
        }
    }

    @Nested
    class SaveCoffeeTest {
        @Test
        void itCanUpdatesTheExistingCoffee() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("Ristretto", 95, 5, 17.5, 20, 0, 0, 60);
            User userTest = new User("TestName", "TestPassword", true);
            userTest.setId(10L);
            CoffeeUser coffeeUserTest = new CoffeeUser("Ristretto", 60, 8, 30, 500, 500, 65, 1000, userTest);
            when(coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(anyString(), anyLong())).thenReturn(true);
            when(coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId("Ristretto", userTest.getId())).thenReturn(coffeeUserTest);

            underTest.saveCoffee(coffeeDtoTest, userTest);

            ArgumentCaptor<CoffeeUser> coffeeUserArgumentCaptor = ArgumentCaptor.forClass(CoffeeUser.class);
            verify(coffeeUserRepository).save(coffeeUserArgumentCaptor.capture());
            CoffeeUser capturedCoffeeUser = coffeeUserArgumentCaptor.getValue();
            assertAll(
                    () -> assertEquals(coffeeDtoTest.getCoffeeName(), capturedCoffeeUser.getCoffeeName()),
                    () -> assertEquals(coffeeDtoTest.getTempWater(), capturedCoffeeUser.getTempWater()),
                    () -> assertEquals(coffeeDtoTest.getGrindingLevel(), capturedCoffeeUser.getGrindingLevel()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfCoffee(), capturedCoffeeUser.getAmountOfCoffee()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfWater(), capturedCoffeeUser.getAmountOfWater()),
                    () -> assertEquals(coffeeDtoTest.getAmountMilk(), capturedCoffeeUser.getAmountMilk()),
                    () -> assertEquals(coffeeDtoTest.getTempMilk(), capturedCoffeeUser.getTempMilk()),
                    () -> assertEquals(userTest, capturedCoffeeUser.getUser())
            );
        }

        @Test
        void itCanCreateNewCoffeeWhenCoffeeDoesNotExists() {
            CoffeeDto coffeeDtoTest = new CoffeeDto("Ristretto", 95, 5, 17.5, 20, 0, 0, 60);
            User userTest = new User("TestName", "TestPassword", true);
            userTest.setId(10L);
            when(coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(anyString(), anyLong())).thenReturn(false);
            when(userService.currentUser()).thenReturn(userTest);
            underTest.saveCoffee(coffeeDtoTest, userTest);

            ArgumentCaptor<CoffeeUser> coffeeUserArgumentCaptor = ArgumentCaptor.forClass(CoffeeUser.class);
            verify(coffeeUserRepository).save(coffeeUserArgumentCaptor.capture());
            CoffeeUser capturedCoffeeUser = coffeeUserArgumentCaptor.getValue();
            assertAll(
                    () -> assertEquals(coffeeDtoTest.getCoffeeName(), capturedCoffeeUser.getCoffeeName()),
                    () -> assertEquals(coffeeDtoTest.getTempWater(), capturedCoffeeUser.getTempWater()),
                    () -> assertEquals(coffeeDtoTest.getGrindingLevel(), capturedCoffeeUser.getGrindingLevel()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfCoffee(), capturedCoffeeUser.getAmountOfCoffee()),
                    () -> assertEquals(coffeeDtoTest.getAmountOfWater(), capturedCoffeeUser.getAmountOfWater()),
                    () -> assertEquals(coffeeDtoTest.getAmountMilk(), capturedCoffeeUser.getAmountMilk()),
                    () -> assertEquals(coffeeDtoTest.getTempMilk(), capturedCoffeeUser.getTempMilk()),
                    () -> assertEquals(userTest, capturedCoffeeUser.getUser())
            );
        }

    }

    @Nested
    class FindCoffeeUserByIdTest {

        @Test
        void canFindCoffeeUserById() {
            User testUser = new User("TestName", "TestPassword", true);
            CoffeeUser testCappuccino = new CoffeeUser("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200, testUser);
            testCappuccino.setId(10L);
            when(coffeeUserRepository.findById(testCappuccino.getId())).thenReturn(Optional.of(testCappuccino));
            CoffeeUser actual = underTest.findCoffeeUserById(testCappuccino.getId());
            assertThat(actual).isEqualTo(testCappuccino);
        }

        @Test
        void cannotFindUserById() {
            Long coffeeIdTest = 10L;

            assertThatThrownBy(
                    () -> underTest.findCoffeeUserById(coffeeIdTest))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Coffee not found");
        }
    }
}