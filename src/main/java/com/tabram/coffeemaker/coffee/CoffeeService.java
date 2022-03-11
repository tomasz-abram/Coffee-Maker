package com.tabram.coffeemaker.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public void addCoffee(Coffee coffee) {

        coffeeRepository.save(coffee);
    }


    public List<Coffee> getCoffee() {
        return coffeeRepository.findAll();
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeRepository.deleteById(coffeeId);

    }
@Transactional
   public void updateCoffee(Long coffeeId, String nameOfCoffee){
   Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(()-> new IllegalStateException("Coffee with id " + coffeeId + " does not exist"));

    if (nameOfCoffee != null && nameOfCoffee.length() > 0 && !Objects.equals(coffee.getNameOfCoffee(),nameOfCoffee)) {
        coffee.setNameOfCoffee(nameOfCoffee);
    }
   }
}

