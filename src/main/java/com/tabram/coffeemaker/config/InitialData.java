package com.tabram.coffeemaker.config;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
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

    @Autowired
    public InitialData(CoffeeUserService coffeeUserService, CoffeeMachineStockRepository coffeeMachineStockRepository) {
        this.coffeeUserService = coffeeUserService;
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
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


        };
    }
}



