package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeUserService {

    private CoffeeUserRepository coffeeUserRepository;
    private CoffeeAdminRepository coffeeAdminRepository;

    @Autowired
    public CoffeeUserService(CoffeeUserRepository coffeeUserRepository, CoffeeAdminRepository coffeeAdminRepository) {
        this.coffeeUserRepository = coffeeUserRepository;
        this.coffeeAdminRepository = coffeeAdminRepository;
    }

    public List<CoffeeUser> addCoffeeUser(User user) {

        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        coffeeAdminRepository.findAll().forEach(coffees -> {
            CoffeeUser coffee = new CoffeeUser(
                    coffees.getNameOfCoffee(),
                    coffees.getTempWater(),
                    coffees.getGrindingLevel(),
                    coffees.getAmountOfCoffee(),
                    coffees.getAmountOfWater(),
                    coffees.getAmountMilk(),
                    coffees.getCupSize(),
                    user);
            coffeeUsers.add(coffee);
        });

        return coffeeUserRepository.saveAll(coffeeUsers);
    }

    public List<CoffeeUser> getCoffee() {
        return coffeeUserRepository.findAll();
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeUserRepository.deleteById(coffeeId);
    }

    @Transactional
    public void updateCoffee(Long userId, String nameOfCoffee) {

    }

}
