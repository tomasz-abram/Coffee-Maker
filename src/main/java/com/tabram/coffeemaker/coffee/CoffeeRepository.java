package com.tabram.coffeemaker.coffee;

import com.tabram.coffeemaker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    @Query("SELECT s FROM Coffee s WHERE s.nameOfCoffee = ?1")
    Optional<Coffee> findCoffeeByName(String name);

}
