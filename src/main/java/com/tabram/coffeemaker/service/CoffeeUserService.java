package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeUserService implements CoffeeUserServiceInterface {

    private final CoffeeUserRepository coffeeUserRepository;
    private final CoffeeAdminRepository coffeeAdminRepository;
    private final UserRepository userRepository;

    @Autowired
    public CoffeeUserService(CoffeeUserRepository coffeeUserRepository, CoffeeAdminRepository coffeeAdminRepository, UserRepository userRepository) {
        this.coffeeUserRepository = coffeeUserRepository;
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.userRepository = userRepository;
    }

    public List<CoffeeUser> addCoffeeListToUser(User user) {

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

    @Override
    public List<CoffeeUser> addOneCoffeeForEachUser(CoffeeDto coffeeDto) {
        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            CoffeeUser coffee = new CoffeeUser(
                    coffeeDto.getNameOfCoffee(),
                    coffeeDto.getTempWater(),
                    coffeeDto.getGrindingLevel(),
                    coffeeDto.getAmountOfCoffee(),
                    coffeeDto.getAmountOfWater(),
                    coffeeDto.getAmountMilk(),
                    coffeeDto.getCupSize(),
                    user);
            coffeeUsers.add(coffee);
        });
        return coffeeUserRepository.saveAll(coffeeUsers);
    }
}
