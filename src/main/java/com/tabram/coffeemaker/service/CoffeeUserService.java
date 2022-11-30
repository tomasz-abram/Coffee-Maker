package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
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
    private final CoffeeAdminService coffeeAdminService;
    private final UserRepository userRepository;

    @Autowired
    public CoffeeUserService(CoffeeUserRepository coffeeUserRepository, CoffeeAdminService coffeeAdminService, UserRepository userRepository) {
        this.coffeeUserRepository = coffeeUserRepository;
        this.coffeeAdminService = coffeeAdminService;
        this.userRepository = userRepository;
    }

    public CoffeeUser findCoffeeUserById(Long coffeeUserId) {
        return coffeeUserRepository.findById(coffeeUserId)
                .orElseThrow(() -> new EntityNotFoundException("Coffee not found"));
    }

    public void saveCoffee(CoffeeUser coffeeUser) {
        coffeeUserRepository.save(coffeeUser);
    }

    public void saveAllCoffees(List<CoffeeUser> coffeeUsersList) {
        coffeeUserRepository.saveAll(coffeeUsersList);
    }

    public CoffeeUser findCoffeeByCoffeeNameAndUsername(String coffeeName, Long userId) {
        return coffeeUserRepository.findCoffeeUserByCoffeeNameAndUserId(coffeeName, userId);
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeUserRepository.deleteById(coffeeId);
    }

    public boolean coffeeExists(CoffeeDto coffeeDto, User user) {
        return coffeeUserRepository.existsCoffeeUserByCoffeeNameAndUserId(coffeeDto.getCoffeeName(), user.getId());
    }

    public void addCoffeeListToUser(User user) {
        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        coffeeAdminService.getAllCoffees().forEach(coffees -> {
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
        user.setCoffeeUser(coffeeUsers);
        userRepository.save(user);
    }

    public void addOneCoffeeForEachUser(CoffeeDto coffeeDto) {
        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (!coffeeExists(coffeeDto, user)) {
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
        saveAllCoffees(coffeeUsers);
    }

    public void saveCoffee(CoffeeDto coffeeDto, User user) {
        coffeeAdminService.checkCoffeeParameters(coffeeDto);
        if (coffeeExists(coffeeDto, user)) {
            CoffeeUser coffeeDB = findCoffeeByCoffeeNameAndUsername(coffeeDto.getCoffeeName(), user.getId());
            coffeeDB.setTempWater(coffeeDto.getTempWater());
            coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
            coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
            coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
            coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
            coffeeDB.setTempMilk(coffeeDto.getTempMilk());
            coffeeDB.setCupSize(coffeeDto.getCupSize());
            saveCoffee(coffeeDB);
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
                    user
            );
            saveCoffee(coffeeUser);
        }
    }

    /* Update exist coffee or creates a new coffee in coffeeUserRepository from coffeeAdminRepository.
       Check the given coffee exist in the user's repository if yes, update, if not create new. */
    public void updateDefaultCoffees(User user) {
        List<CoffeeAdmin> coffees = coffeeAdminService.getAllCoffees();
        List<CoffeeUser> coffeesUser = new ArrayList<>();
        coffees.forEach(coffeeAdmin -> {
            CoffeeUser coffeeUser = findCoffeeByCoffeeNameAndUsername(coffeeAdmin.getCoffeeName(), user.getId());
            if (coffeeUser == null) {
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
        saveAllCoffees(coffeesUser);
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
