package com.tabram.coffeemaker.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User mark = new User("Mark");
            User tony = new User("Tony");

            repository.saveAll(List.of(mark, tony));
        };

    }


}
