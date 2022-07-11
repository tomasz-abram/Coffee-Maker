package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineConstantValueRepository extends JpaRepository<CoffeeMachineConstantValue, Long> {

    CoffeeMachineConstantValue findByName(String name);
}
