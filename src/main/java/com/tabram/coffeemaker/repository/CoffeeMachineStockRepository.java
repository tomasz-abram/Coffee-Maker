package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeMachineStockRepository extends JpaRepository<CoffeeMachineStock, Long> {

    CoffeeMachineStock findByName(String name);

}
