package com.tabram.coffeemaker.coffee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {


    @Query("SELECT c FROM Coffee c WHERE c.nameOfCoffee = ?1")
    Optional<Coffee> findCoffeeByName(String nameOfCoffee);


}
