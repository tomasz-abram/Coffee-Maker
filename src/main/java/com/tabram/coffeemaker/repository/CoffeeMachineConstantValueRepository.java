package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.config.CoffeeMachineConstantValues;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineConstantValueRepository extends CrudRepository<CoffeeMachineConstantValues, Long> {

    CoffeeMachineConstantValues findByName(String name);
}
