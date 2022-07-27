package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeUserService {

    private final CoffeeUserRepository coffeeUserRepository;
    private final CoffeeAdminRepository coffeeAdminRepository;
    private final UserRepository userRepository;
    private final CoffeeAdminService coffeeAdminService;
    private final UserService userService;

    @Autowired
    public CoffeeUserService(CoffeeUserRepository coffeeUserRepository, CoffeeAdminRepository coffeeAdminRepository, UserRepository userRepository, CoffeeAdminService coffeeAdminService, UserService userService) {
        this.coffeeUserRepository = coffeeUserRepository;
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.userRepository = userRepository;
        this.coffeeAdminService = coffeeAdminService;
        this.userService = userService;
    }

    public CoffeeUser findCoffeeUserById(Long coffeeUserId) {
        return coffeeUserRepository.findById(coffeeUserId)
                .orElseThrow(() -> new EntityNotFoundException("Coffee not found"));
    }

    public CoffeeUser findCoffeeByCoffeeNameAndUserId(String coffeeName, Long userId) {
        return coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId(coffeeName, userId);
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeUserRepository.deleteById(coffeeId);
    }

    public void addCoffeeListToUser(User user) {

        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        coffeeAdminRepository.findAll().forEach(coffees -> {
            CoffeeUser coffee = new CoffeeUser(
                    coffees.getCoffeeName(),
                    coffees.getTempWater(),
                    coffees.getGrindingLevel(),
                    coffees.getAmountOfCoffee(),
                    coffees.getAmountOfWater(),
                    coffees.getAmountMilk(),
                    coffees.getTempMilk(),
                    coffees.getCupSize(),
                    user);
            coffeeUsers.add(coffee);
        });
        coffeeUserRepository.saveAll(coffeeUsers);
    }

    public void addOneCoffeeForEachUser(CoffeeDto coffeeDto) {
        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (!coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(coffeeDto.getCoffeeName(), user.getId())) {
                CoffeeUser coffee = new CoffeeUser(
                        coffeeDto.getCoffeeName(),
                        coffeeDto.getTempWater(),
                        coffeeDto.getGrindingLevel(),
                        coffeeDto.getAmountOfCoffee(),
                        coffeeDto.getAmountOfWater(),
                        coffeeDto.getAmountMilk(),
                        coffeeDto.getTempMilk(),
                        coffeeDto.getCupSize(),
                        user);
                coffeeUsers.add(coffee);
            }
        });
        coffeeUserRepository.saveAll(coffeeUsers);
    }

    public void saveCoffee(CoffeeDto coffeeDto, User user) {

        coffeeAdminService.checkCoffeeParameters(coffeeDto);

        if (coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(coffeeDto.getCoffeeName(), user.getId())) {
            CoffeeUser coffeeDB = coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId(coffeeDto.getCoffeeName(), user.getId());
            coffeeDB.setTempWater(coffeeDto.getTempWater());
            coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
            coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
            coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
            coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
            coffeeDB.setTempMilk(coffeeDto.getTempMilk());
            coffeeDB.setCupSize(coffeeDto.getCupSize());
            coffeeUserRepository.save(coffeeDB);
        } else {
            CoffeeUser coffeeUser = new CoffeeUser(
                    coffeeDto.getCoffeeName(),
                    coffeeDto.getTempWater(),
                    coffeeDto.getGrindingLevel(),
                    coffeeDto.getAmountOfCoffee(),
                    coffeeDto.getAmountOfWater(),
                    coffeeDto.getAmountMilk(),
                    coffeeDto.getTempMilk(),
                    coffeeDto.getCupSize(),
                    userService.currentUser());
            coffeeUserRepository.save(coffeeUser);
        }
    }

    /* Update exist coffee or creates a new coffee in coffeeUserRepository from coffeeAdminRepository.
       Check the given coffee exist in the user's repository if yes, update, if not create new. */
    public void updateDefaultCoffees(User user) {

        List<CoffeeAdmin> coffees = coffeeAdminRepository.findAll();
        List<CoffeeUser> coffeesUser = new ArrayList<>();
        coffees.forEach(coffeeAdmin -> {
            CoffeeUser coffeeUser = coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId(coffeeAdmin.getCoffeeName(), user.getId());
            if (!coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(coffeeAdmin.getCoffeeName(), user.getId())) {
                CoffeeUser newCoffee = new CoffeeUser(
                        coffeeAdmin.getCoffeeName(),
                        coffeeAdmin.getTempWater(),
                        coffeeAdmin.getGrindingLevel(),
                        coffeeAdmin.getAmountOfCoffee(),
                        coffeeAdmin.getAmountOfWater(),
                        coffeeAdmin.getAmountMilk(),
                        coffeeAdmin.getTempMilk(),
                        coffeeAdmin.getCupSize(),
                        user);
                coffeesUser.add(newCoffee);
            } else {
                coffeeUser.setTempWater(coffeeAdmin.getTempWater());
                coffeeUser.setGrindingLevel(coffeeAdmin.getGrindingLevel());
                coffeeUser.setAmountOfCoffee(coffeeAdmin.getAmountOfCoffee());
                coffeeUser.setAmountOfWater(coffeeAdmin.getAmountOfWater());
                coffeeUser.setAmountMilk(coffeeAdmin.getAmountMilk());
                coffeeUser.setTempMilk(coffeeAdmin.getTempMilk());
                coffeeUser.setCupSize(coffeeAdmin.getCupSize());
                coffeesUser.add(coffeeUser);
            }
        });
        coffeeUserRepository.saveAll(coffeesUser);
    }

    public Double tempCoffee(CoffeeUser coffeeUser) {
        double w = coffeeUser.getAmountOfWater();
        double wT = coffeeUser.getTempWater();
        double m = coffeeUser.getAmountMilk();
        double mT = coffeeUser.getTempMilk();
        double temp = ((w * wT) + (m * mT)) / (m + w);
        return BigDecimal.valueOf(temp)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
