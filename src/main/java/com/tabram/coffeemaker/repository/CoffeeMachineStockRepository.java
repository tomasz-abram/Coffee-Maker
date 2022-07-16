package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineStockRepository extends JpaRepository<CoffeeMachineStock, Long> {

    CoffeeMachineStock findByName(String name);
}
