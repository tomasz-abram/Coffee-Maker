package com.tabram.coffeemaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class )
public class CoffeemakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeemakerApplication.class, args);
	}



}
