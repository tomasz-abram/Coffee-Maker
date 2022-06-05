package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeUserRepository extends JpaRepository<CoffeeUser, Long> {

    CoffeeUser findAllCoffeeUserByUserId (Long userId);

}
