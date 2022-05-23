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

}
