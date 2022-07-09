package com.tabram.coffeemaker.config;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeMachineConstantValueRepository;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class InitialData {

    private final CoffeeUserService coffeeUserService;
    private final CoffeeMachineStockRepository coffeeMachineStockRepository;

    private final CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository;

    @Autowired
    public InitialData(CoffeeUserService coffeeUserService, CoffeeMachineStockRepository coffeeMachineStockRepository, CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository) {
        this.coffeeUserService = coffeeUserService;
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
        this.coffeeMachineConstantValueRepository = coffeeMachineConstantValueRepository;
    }

    @Bean
    CommandLineRunner coffeeCommandLineRunner(CoffeeAdminRepository coffeeAdminRepository, UserRepository userRepository) {
        return args -> {
            CoffeeAdmin espresso = new CoffeeAdmin("Espresso", 95, 5, 17.5, 40, 0, 0, 60);
            CoffeeAdmin cappuccino = new CoffeeAdmin("Cappuccino", 95, 5, 17.5, 30, 100, 65, 200);
            CoffeeAdmin latteMacchiato = new CoffeeAdmin("LatteMacchiato", 95, 5, 17.5, 40, 120, 65, 200);
            CoffeeAdmin lungo = new CoffeeAdmin("Lungo", 95, 5, 17.5, 150, 0, 65, 200);
            CoffeeAdmin macchiato = new CoffeeAdmin("Macchiato", 95, 5, 17.5, 40, 10, 65, 100);
            CoffeeAdmin ristretto = new CoffeeAdmin("Ristretto", 95, 5, 17.5, 20, 0, 0, 60);

            List<CoffeeAdmin> coffees = List.of(espresso, cappuccino, latteMacchiato, lungo, macchiato, ristretto);
            coffeeAdminRepository.saveAllAndFlush(coffees);

            User userAdmin = new User("Admin", new BCryptPasswordEncoder().encode("Admin"), true, List.of(new Role("ROLE_ADMIN")));
            User userTest = new User("UserTest", new BCryptPasswordEncoder().encode("UserTest"), true, List.of(new Role("ROLE_USER")));
            User userDefault = new User("Default", new BCryptPasswordEncoder().encode("Default"), true, List.of(new Role("ROLE_DEFAULT")));
            List<User> users = List.of(userAdmin, userTest, userDefault);
            userRepository.saveAll(users);

            coffeeUserService.addCoffeeListToUser(userAdmin);
            coffeeUserService.addCoffeeListToUser(userTest);
            coffeeUserService.addCoffeeListToUser(userDefault);

            CoffeeMachineStock waterStock = new CoffeeMachineStock("Water", 500, "ml", "");
            CoffeeMachineStock milkStock = new CoffeeMachineStock("Milk", 300, "ml", "");
            CoffeeMachineStock coffeeBeansStock = new CoffeeMachineStock("Coffee beans", 200, "g", "");
            CoffeeMachineStock groundContainerStock = new CoffeeMachineStock("Ground container", 25, "pcs", "");
            CoffeeMachineStock waterHardnessStock = new CoffeeMachineStock("Water hardness", 5, "mmol/l", "");
            CoffeeMachineStock descaleCounterStock = new CoffeeMachineStock("Descale counter", 2000, "", "");

            List<CoffeeMachineStock> stocks = List.of(waterStock, milkStock, coffeeBeansStock, groundContainerStock, waterHardnessStock, descaleCounterStock);
            coffeeMachineStockRepository.saveAll(stocks);


            List<CoffeeMachineConstantValues> listOfConstants = List.of(
                    new CoffeeMachineConstantValues("min_water_container", 0),
                    new CoffeeMachineConstantValues("max_water_container", 3000),
                    new CoffeeMachineConstantValues("min_milk_container", 0),
                    new CoffeeMachineConstantValues("max_milk_container", 1500),
                    new CoffeeMachineConstantValues("min_coffee_beans_container", 0),
                    new CoffeeMachineConstantValues("max_coffee_beans_container", 300),
                    new CoffeeMachineConstantValues("min_ground_container", 0),
                    new CoffeeMachineConstantValues("max_ground_container", 30),
                    new CoffeeMachineConstantValues("max_descale_counter", 50000),
                    new CoffeeMachineConstantValues("max_grinding_level", 10),
                    new CoffeeMachineConstantValues("min_grinding_level", 1),
                    new CoffeeMachineConstantValues("max_amount_of_coffee", 30),
                    new CoffeeMachineConstantValues("min_amount_of_coffee", 10),
                    new CoffeeMachineConstantValues("max_temp_water", 98),
                    new CoffeeMachineConstantValues("min_temp_water", 30),
                    new CoffeeMachineConstantValues("max_amount_of_water", 500),
                    new CoffeeMachineConstantValues("min_amount_of_water", 15),
                    new CoffeeMachineConstantValues("max_temp_milk", 90),
                    new CoffeeMachineConstantValues("min_temp_milk", 1),
                    new CoffeeMachineConstantValues("max_amount_of_milk", 500),
                    new CoffeeMachineConstantValues("min_amount_of_milk", 0),
                    new CoffeeMachineConstantValues("max_cup_size", 1000),
                    new CoffeeMachineConstantValues("min_cup_size", 15),
                    new CoffeeMachineConstantValues("warning_level_water", 500),
                    new CoffeeMachineConstantValues("danger_level_water", 200),
                    new CoffeeMachineConstantValues("warning_level_milk", 500),
                    new CoffeeMachineConstantValues("danger_level_milk", 200),
                    new CoffeeMachineConstantValues("warning_level_coffee_beans", 90),
                    new CoffeeMachineConstantValues("danger_level_coffee_beans", 20),
                    new CoffeeMachineConstantValues("warning_level_ground_container", 3),
                    new CoffeeMachineConstantValues("danger_level_ground_container", 1),
                    new CoffeeMachineConstantValues("warning_level_descale", 5000),
                    new CoffeeMachineConstantValues("danger_level_descale", 2000)
            );
            coffeeMachineConstantValueRepository.saveAll(listOfConstants);
        };
    }
}


