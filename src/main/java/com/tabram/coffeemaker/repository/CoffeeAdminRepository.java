package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeAdminRepository extends JpaRepository<CoffeeAdmin, Long> {

    CoffeeAdmin findCoffeeAdminByCoffeeName(String coffeeName);

    boolean existsCoffeeAdminByCoffeeName(String coffeeName);
}
