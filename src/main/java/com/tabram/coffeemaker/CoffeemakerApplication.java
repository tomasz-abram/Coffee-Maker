package com.tabram.coffeemaker;

import com.tabram.coffeemaker.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CoffeemakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeemakerApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner mappingDemo(UserRepository userRepository,
//                                         CoffeeRepository coffeeRepository) {
//        return args -> {
//
//            User mark = new User("Mark", "password", true, "User");
//            User tony = new User("Tony", "password", true, "User");
//
//            userRepository.saveAll(List.of(mark, tony));
//
//            Coffee espresso = new Coffee("Espresso", 95, 5, 17.5, 40, 0, 60);
//            Coffee cappuccino = new Coffee("Cappuccino", 95, 5, 17.5, 30, 100, 200);
//            Coffee latteMacchiato = new Coffee("LatteMacchiato", 95, 5, 17.5, 40, 120, 200);
//            Coffee lungo = new Coffee("Lungo", 95, 5, 17.5, 150, 0, 200);
//            Coffee macchiato = new Coffee("Macchiato", 95, 5, 17.5, 40, 10, 100);
//            Coffee ristretto = new Coffee("Ristretto", 95, 5, 17.5, 20, 0, 60);
//
//            coffeeRepository.saveAll(List.of(espresso, cappuccino, latteMacchiato, lungo, macchiato, ristretto));
//
//            mark.getCoffeeList().addAll(List.of(espresso, cappuccino, latteMacchiato, lungo, macchiato, ristretto));
//            tony.getCoffeeList().addAll(List.of(espresso, cappuccino, latteMacchiato, lungo, macchiato, ristretto));
//            userRepository.saveAll(List.of(mark, tony));
//
//
//        };
//    }

}
