package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeUserService {

    private final CoffeeUserRepository coffeeUserRepository;
    private final CoffeeAdminRepository coffeeAdminRepository;
    private final UserRepository userRepository;

    @Autowired
    public CoffeeUserService(CoffeeUserRepository coffeeUserRepository, CoffeeAdminRepository coffeeAdminRepository, UserRepository userRepository) {
        this.coffeeUserRepository = coffeeUserRepository;
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.userRepository = userRepository;
    }

    public void addCoffeeListToUser(User user) {

        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        coffeeAdminRepository.findAll().forEach(coffees -> {
            CoffeeUser coffee = new CoffeeUser(
                    coffees.getNameOfCoffee(),
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

    public List<CoffeeUser> getCoffee() {
        return coffeeUserRepository.findAll();
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeUserRepository.deleteById(coffeeId);
    }

    public User currentUser() {
        User currentUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUser = userRepository.findByUserName(authentication.getName());
        }
        return currentUser;
    }

    public void addOneCoffeeForEachUser(CoffeeDto coffeeDto) {
        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            CoffeeUser coffee = new CoffeeUser(
                    coffeeDto.getNameOfCoffee(),
                    coffeeDto.getTempWater(),
                    coffeeDto.getGrindingLevel(),
                    coffeeDto.getAmountOfCoffee(),
                    coffeeDto.getAmountOfWater(),
                    coffeeDto.getAmountMilk(),
                    coffeeDto.getTempMilk(),
                    coffeeDto.getCupSize(),
                    user);
            coffeeUsers.add(coffee);
        });
        coffeeUserRepository.saveAll(coffeeUsers);
    }

    public void saveCoffee(CoffeeDto coffeeDto, User user) {

        if (coffeeUserRepository.findCoffeeUserByNameOfCoffeeAndUserId(coffeeDto.getNameOfCoffee(), user.getId()) != null) {
            CoffeeUser coffeeDB = coffeeUserRepository.findCoffeeUserByNameOfCoffeeAndUserId(coffeeDto.getNameOfCoffee(), user.getId());
            coffeeDB.setTempWater(coffeeDto.getTempWater());
            coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
            coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
            coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
            coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
            coffeeDB.setTempMilk(coffeeDto.getTempMilk());
            coffeeDB.setCupSize(coffeeDB.getCupSize());
            coffeeUserRepository.save(coffeeDB);
        } else {
            CoffeeUser coffeeUser = new CoffeeUser(
                    coffeeDto.getNameOfCoffee(),
                    coffeeDto.getTempWater(),
                    coffeeDto.getGrindingLevel(),
                    coffeeDto.getAmountOfCoffee(),
                    coffeeDto.getAmountOfWater(),
                    coffeeDto.getAmountMilk(),
                    coffeeDto.getTempMilk(),
                    coffeeDto.getCupSize(),
                    currentUser());

             coffeeUserRepository.save(coffeeUser);
        }
    }

    // Update exist coffee or creates a new coffee in coffeeUserRepository from coffeeAdminRepository.
// Check the given coffee exist in the user's repository if yes, update, if not create new.
    public void updateDefaultCoffees(User user) {

        List<CoffeeAdmin> coffees = coffeeAdminRepository.findAll();
        List<CoffeeUser> coffeesUser = new ArrayList<>();

        coffees.forEach(coffeeAdmin -> {

            CoffeeUser cU = coffeeUserRepository.findCoffeeUserByNameOfCoffeeAndUserId(coffeeAdmin.getNameOfCoffee(), user.getId());

            if (cU == null) {
                CoffeeUser newCoffee = new CoffeeUser(
                        coffeeAdmin.getNameOfCoffee(),
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
                cU.setTempWater(coffeeAdmin.getTempWater());
                cU.setGrindingLevel(coffeeAdmin.getGrindingLevel());
                cU.setAmountOfCoffee(coffeeAdmin.getAmountOfCoffee());
                cU.setAmountOfWater(coffeeAdmin.getAmountOfWater());
                cU.setAmountMilk(coffeeAdmin.getAmountMilk());
                cU.setTempMilk(coffeeAdmin.getTempMilk());
                cU.setCupSize(coffeeAdmin.getCupSize());
                coffeesUser.add(cU);
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
