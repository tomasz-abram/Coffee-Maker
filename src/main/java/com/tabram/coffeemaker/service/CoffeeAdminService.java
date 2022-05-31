package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeAdminDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeAdminService implements CoffeeAdminServiceInterface {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final UserRepository userRepository;
    private CoffeeUserRepository coffeeUserRepository;

    @Autowired
    public CoffeeAdminService(CoffeeAdminRepository coffeeAdminRepository, UserRepository userRepository, CoffeeUserRepository coffeeUserRepository) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.userRepository = userRepository;
        this.coffeeUserRepository = coffeeUserRepository;
    }

    public List<CoffeeAdmin> getCoffee() {
        return coffeeAdminRepository.findAll();
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeAdminRepository.deleteById(coffeeId);

    }

    @Override
    public CoffeeAdmin saveCoffee(CoffeeAdminDto coffeeAdminDto) {
        Optional<CoffeeAdmin> coffeeOptional = coffeeAdminRepository.findCoffeeByName(coffeeAdminDto.getNameOfCoffee());
        if (coffeeOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }

        CoffeeAdmin coffeeAdmin = new CoffeeAdmin(
                coffeeAdminDto.getNameOfCoffee(),
                coffeeAdminDto.getTempWater(),
                coffeeAdminDto.getGrindingLevel(),
                coffeeAdminDto.getAmountOfCoffee(),
                coffeeAdminDto.getAmountOfWater(),
                coffeeAdminDto.getAmountMilk(),
                coffeeAdminDto.getCupSize()
        );

        List<CoffeeUser> coffeeUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            CoffeeUser coffee = new CoffeeUser(
                    coffeeAdminDto.getNameOfCoffee(),
                    coffeeAdminDto.getTempWater(),
                    coffeeAdminDto.getGrindingLevel(),
                    coffeeAdminDto.getAmountOfCoffee(),
                    coffeeAdminDto.getAmountOfWater(),
                    coffeeAdminDto.getAmountMilk(),
                    coffeeAdminDto.getCupSize(),
                    user);

            coffeeUsers.add(coffee);
        });

        coffeeUserRepository.saveAllAndFlush(coffeeUsers);
        return coffeeAdminRepository.save(coffeeAdmin);
    }
}
