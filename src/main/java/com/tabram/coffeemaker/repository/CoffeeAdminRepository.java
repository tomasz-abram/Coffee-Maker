package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeAdminRepository extends JpaRepository<CoffeeAdmin, Long> {

    Optional<CoffeeAdmin> findCoffeeAdminByCoffeeName(String coffeeName);
    Optional<CoffeeAdmin> findCoffeeAdminById(Long id);

    void deleteCoffeeAdminByCoffeeName(String coffeeName);

    boolean existsCoffeeAdminByCoffeeName(String coffeeName);
}
