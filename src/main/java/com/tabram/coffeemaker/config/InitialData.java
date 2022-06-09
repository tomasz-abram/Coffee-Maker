package com.tabram.coffeemaker.config;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeMachineStatus;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeMachineStatusRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InitialData {

    private final CoffeeUserService coffeeUserService;
    private final CoffeeMachineStatusRepository coffeeMachineStatusRepository;

    @Autowired
    public InitialData(CoffeeUserService coffeeUserService, CoffeeMachineStatusRepository coffeeMachineStatusRepository) {
        this.coffeeUserService = coffeeUserService;
        this.coffeeMachineStatusRepository = coffeeMachineStatusRepository;
    }

    @Bean
    CommandLineRunner coffeeCommandLineRunner(CoffeeAdminRepository coffeeAdminRepository, UserRepository userRepository) {
        return args -> {
            CoffeeAdmin espresso = new CoffeeAdmin("Espresso", 95, 5, 17.5, 40, 0, 60);
            CoffeeAdmin cappuccino = new CoffeeAdmin("Cappuccino", 95, 5, 17.5, 30, 100, 200);
            CoffeeAdmin latteMacchiato = new CoffeeAdmin("LatteMacchiato", 95, 5, 17.5, 40, 120, 200);
            CoffeeAdmin lungo = new CoffeeAdmin("Lungo", 95, 5, 17.5, 150, 0, 200);
            CoffeeAdmin macchiato = new CoffeeAdmin("Macchiato", 95, 5, 17.5, 40, 10, 100);
            CoffeeAdmin ristretto = new CoffeeAdmin("Ristretto", 95, 5, 17.5, 20, 0, 60);

            List<CoffeeAdmin> coffees = List.of(espresso, cappuccino, latteMacchiato, lungo, macchiato, ristretto);
            coffeeAdminRepository.saveAllAndFlush(coffees);

            User userAdmin = new User("Admin", new BCryptPasswordEncoder().encode("Admin"), true, Arrays.asList(new Role("ROLE_ADMIN")));
            User userDefault = new User("Default", new BCryptPasswordEncoder().encode("Default"), true, Arrays.asList(new Role("ROLE_DEFAULT")));
            List<User> users = List.of(userAdmin, userDefault);
            userRepository.saveAll(users);


            coffeeUserService.addCoffeeListToUser(userDefault);


            CoffeeMachineStatus coffeeMachineStatus = new CoffeeMachineStatus(250, 150, 200, 23, 5);
            coffeeMachineStatusRepository.save(coffeeMachineStatus);


        };
    }
}



