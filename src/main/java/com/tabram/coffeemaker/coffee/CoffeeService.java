package com.tabram.coffeemaker.coffee;


import com.tabram.coffeemaker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class CoffeeService {
    private UserRepository userRepository;
    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    public void addCoffee(Coffee coffee) {
        Optional<Coffee> coffeeOptional = coffeeRepository.findCoffeeByName(coffee.getNameOfCoffee());
        if (coffeeOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        coffeeRepository.save(coffee);
    }

    public List<Coffee> getCoffee() {
        return coffeeRepository.findAll();
    }

    public void deleteCoffee(Integer coffeeId) {
        coffeeRepository.deleteById(coffeeId);

    }

    @Transactional
    public void updateCoffee(Integer coffeeId, String nameOfCoffee) {
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(() -> new IllegalStateException("Coffee with id " + coffeeId + " does not exist"));

        if (nameOfCoffee != null && nameOfCoffee.length() > 0 && !Objects.equals(coffee.getNameOfCoffee(), nameOfCoffee)) {
            coffee.setNameOfCoffee(nameOfCoffee);
        }
    }
}

