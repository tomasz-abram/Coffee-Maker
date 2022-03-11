package com.tabram.coffeemaker.coffee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CoffeeConfig {

    @Bean
    CommandLineRunner CoffeeCommandLineRunner(CoffeeRepository repository) {
        return args -> {
            Coffee espresso = new Coffee("Espresso", 95, 5, 17.5, 40, 0, 60);
            Coffee cappuccino = new Coffee("Cappuccino", 95, 5, 17.5, 30, 100, 200);
            Coffee latteMacchiato = new Coffee("LatteMacchiato", 95, 5, 17.5, 40, 120, 200);
            Coffee lungo = new Coffee("Lungo", 95, 5, 17.5, 150, 0, 200);
            Coffee macchiato = new Coffee("Macchiato", 95, 5, 17.5, 40, 10, 100);
            Coffee ristretto = new Coffee("Ristretto", 95, 5, 17.5, 20, 0, 60);

            repository.saveAll(List.of(espresso, cappuccino, latteMacchiato, lungo, macchiato, ristretto));
        };

    }
}