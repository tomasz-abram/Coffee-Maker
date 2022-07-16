package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeUserRepository extends JpaRepository<CoffeeUser, Long> {

    CoffeeUser findCoffeeUserByCoffeeNameAndUserId(String coffeeName, Long userId);

    boolean existsCoffeeUserByCoffeeNameAndUserId(String coffeeName, Long userId);

}
