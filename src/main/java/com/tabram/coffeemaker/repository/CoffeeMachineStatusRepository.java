package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeMachineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineStatusRepository extends JpaRepository<CoffeeMachineStatus, Long> {


}
