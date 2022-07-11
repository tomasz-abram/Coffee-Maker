package com.tabram.coffeemaker.config;

import com.tabram.coffeemaker.model.*;
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
import java.util.Set;

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

            User userAdmin = new User("Admin", new BCryptPasswordEncoder().encode("Admin"), true, Set.of(new Role("ROLE_ADMIN")));
            User userTest = new User("UserTest", new BCryptPasswordEncoder().encode("UserTest"), true, Set.of(new Role("ROLE_USER")));
            User userDefault = new User("Default", new BCryptPasswordEncoder().encode("Default"), true, Set.of(new Role("ROLE_DEFAULT")));
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


            List<CoffeeMachineConstantValue> listOfConstants = List.of(
                    new CoffeeMachineConstantValue("min_water_container", 0),
                    new CoffeeMachineConstantValue("max_water_container", 3000),
                    new CoffeeMachineConstantValue("min_milk_container", 0),
                    new CoffeeMachineConstantValue("max_milk_container", 1500),
                    new CoffeeMachineConstantValue("min_coffee_beans_container", 0),
                    new CoffeeMachineConstantValue("max_coffee_beans_container", 300),
                    new CoffeeMachineConstantValue("min_ground_container", 0),
                    new CoffeeMachineConstantValue("max_ground_container", 30),
                    new CoffeeMachineConstantValue("max_descale_counter", 50000),
                    new CoffeeMachineConstantValue("max_grinding_level", 10),
                    new CoffeeMachineConstantValue("min_grinding_level", 1),
                    new CoffeeMachineConstantValue("max_amount_of_coffee", 30),
                    new CoffeeMachineConstantValue("min_amount_of_coffee", 10),
                    new CoffeeMachineConstantValue("max_temp_water", 98),
                    new CoffeeMachineConstantValue("min_temp_water", 30),
                    new CoffeeMachineConstantValue("max_amount_of_water", 500),
                    new CoffeeMachineConstantValue("min_amount_of_water", 15),
                    new CoffeeMachineConstantValue("max_temp_milk", 90),
                    new CoffeeMachineConstantValue("min_temp_milk", 1),
                    new CoffeeMachineConstantValue("max_amount_of_milk", 500),
                    new CoffeeMachineConstantValue("min_amount_of_milk", 0),
                    new CoffeeMachineConstantValue("max_cup_size", 1000),
                    new CoffeeMachineConstantValue("min_cup_size", 15),
                    new CoffeeMachineConstantValue("warning_level_water", 500),
                    new CoffeeMachineConstantValue("danger_level_water", 200),
                    new CoffeeMachineConstantValue("warning_level_milk", 500),
                    new CoffeeMachineConstantValue("danger_level_milk", 200),
                    new CoffeeMachineConstantValue("warning_level_coffee_beans", 90),
                    new CoffeeMachineConstantValue("danger_level_coffee_beans", 20),
                    new CoffeeMachineConstantValue("warning_level_ground_container", 3),
                    new CoffeeMachineConstantValue("danger_level_ground_container", 1),
                    new CoffeeMachineConstantValue("warning_level_descale", 5000),
                    new CoffeeMachineConstantValue("danger_level_descale", 2000)
            );
            coffeeMachineConstantValueRepository.saveAll(listOfConstants);
        };
    }
}


