package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CoffeeMachineStockService {


    private final CoffeeMachineStockRepository coffeeMachineStockRepository;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    @Autowired
    public CoffeeMachineStockService(CoffeeMachineStockRepository coffeeMachineStockRepository, CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    public void updateWater(int addWater) {
        CoffeeMachineStock waterStock = coffeeMachineStockRepository.findByName("Water");
        float updateWater = (Objects.requireNonNull(waterStock).getValue() + addWater);
        if (updateWater > coffeeMachineConstantValueService.getMaxWaterContainer()) {
            waterStock.setValue(coffeeMachineConstantValueService.getMaxWaterContainer());
        } else {
            waterStock.setValue(updateWater);
        }
        coffeeMachineStockRepository.save(waterStock);
    }

    public void emptyWater() {
        CoffeeMachineStock waterStock = coffeeMachineStockRepository.findByName("Water");
        Objects.requireNonNull(waterStock).setValue(coffeeMachineConstantValueService.getMinWaterContainer());
        coffeeMachineStockRepository.save(waterStock);
    }

    public void updateMilk(int addMilk) {
        CoffeeMachineStock milkStock = coffeeMachineStockRepository.findByName("Milk");
        float updateMilk = (Objects.requireNonNull(milkStock).getValue() + addMilk);

        if (updateMilk > coffeeMachineConstantValueService.getMaxMilkContainer()) {
            milkStock.setValue(coffeeMachineConstantValueService.getMaxMilkContainer());
        } else {
            milkStock.setValue(updateMilk);
        }
        coffeeMachineStockRepository.save(milkStock);
    }

    public void emptyMilk() {
        CoffeeMachineStock milkStock = coffeeMachineStockRepository.findByName("Milk");
        Objects.requireNonNull(milkStock).setValue(coffeeMachineConstantValueService.getMinMilkContainer());
        coffeeMachineStockRepository.save(milkStock);
    }

    public void updateBeans(int addBeans) {
        CoffeeMachineStock beansStock = coffeeMachineStockRepository.findByName("Coffee beans");
        float updateBeans = Objects.requireNonNull(beansStock).getValue() + addBeans;

        if (updateBeans > coffeeMachineConstantValueService.getMaxCoffeeBeansContainer()) {
            beansStock.setValue(coffeeMachineConstantValueService.getMaxCoffeeBeansContainer());
        } else {
            beansStock.setValue(updateBeans);
        }
        coffeeMachineStockRepository.save(beansStock);
    }

    public void emptyCoffeeBeans() {
        CoffeeMachineStock beansStock = coffeeMachineStockRepository.findByName("Coffee beans");
        Objects.requireNonNull(beansStock).setValue(coffeeMachineConstantValueService.getMinCoffeeBeansContainer());
        coffeeMachineStockRepository.save(beansStock);
    }

    public void emptyGroundContainer() {
        CoffeeMachineStock groundContainerStock = coffeeMachineStockRepository.findByName("Ground container");
        Objects.requireNonNull(groundContainerStock).setValue(0);
        coffeeMachineStockRepository.save(groundContainerStock);
    }

    public void descale() {
        CoffeeMachineStock descaleStock = coffeeMachineStockRepository.findByName("Descale counter");
        Objects.requireNonNull(descaleStock).setValue(0);
        coffeeMachineStockRepository.save(descaleStock);
    }

    public void updateWaterHardness(float setHardness) {
        CoffeeMachineStock waterHardnessStock = coffeeMachineStockRepository.findByName("Water hardness");
        if (setHardness < 0) {
            throw new IllegalArgumentException("The water hardness must not be less than zero");
        }
        if (setHardness > 100) {
            throw new IllegalArgumentException("If you don't pour concrete over your coffee, this value is probably lower :)");
        }
        Objects.requireNonNull(waterHardnessStock).setValue(setHardness);
        coffeeMachineStockRepository.save(waterHardnessStock);
    }

    public void checkStockStatus() {
        CoffeeMachineStock waterStock = coffeeMachineStockRepository.findByName("Water");
        CoffeeMachineStock milkStock = coffeeMachineStockRepository.findByName("Milk");
        CoffeeMachineStock coffeeBeansStock = coffeeMachineStockRepository.findByName("Coffee beans");
        CoffeeMachineStock groundContainerStock = coffeeMachineStockRepository.findByName("Ground container");
        CoffeeMachineStock descaleCounter = coffeeMachineStockRepository.findByName("Descale counter");

        if (waterStock.getValue() > coffeeMachineConstantValueService.getWarningLevelWater()) {
            waterStock.setStatus("Ok");
        } else if (waterStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelWater()) {
            waterStock.setStatus("Warning");
            if (waterStock.getValue() <= coffeeMachineConstantValueService.getDangerLevelWater()) {
                waterStock.setStatus("Danger");
            }
        }

        if (milkStock.getValue() > coffeeMachineConstantValueService.getWarningLevelMilk()) {
            milkStock.setStatus("Ok");
        } else if (milkStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelMilk()) {
            milkStock.setStatus("Warning");
            if (milkStock.getValue() <= coffeeMachineConstantValueService.getDangerLevelMilk()) {
                milkStock.setStatus("Danger");
            }
        }

        if (coffeeBeansStock.getValue() > coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()) {
            coffeeBeansStock.setStatus("Ok");
        } else if (coffeeBeansStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()) {
            coffeeBeansStock.setStatus("Warning");
            if (coffeeBeansStock.getValue() <= coffeeMachineConstantValueService.getDangerLevelCoffeeBeans()) {
                coffeeBeansStock.setStatus("Danger");
            }
        }

        if (groundContainerStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelGroundContainer()) {
            groundContainerStock.setStatus("Ok");
        } else if (groundContainerStock.getValue() >= coffeeMachineConstantValueService.getWarningLevelGroundContainer()) {
            groundContainerStock.setStatus("Warning");
            if (groundContainerStock.getValue() >= coffeeMachineConstantValueService.getDangerLevelGroundContainer()) {
                groundContainerStock.setStatus("Danger");
            }
        }

        if (descaleCounter.getValue() < coffeeMachineConstantValueService.getWarningLevelDescale()) {
            descaleCounter.setStatus("Ok");
        } else if (descaleCounter.getValue() >= coffeeMachineConstantValueService.getWarningLevelDescale()) {
            descaleCounter.setStatus("Warning");
            if (descaleCounter.getValue() >= coffeeMachineConstantValueService.getDangerLevelDescale()) {
                descaleCounter.setStatus("Danger");
            }
        }

        List<CoffeeMachineStock> stocks = List.of(waterStock, milkStock, coffeeBeansStock, groundContainerStock, descaleCounter);
        coffeeMachineStockRepository.saveAll(stocks);
    }

    public String alarmLightStockStatus() {
        String alarmLight;
        List<String> lCMS = new ArrayList<>();
        coffeeMachineStockRepository.findAll().forEach(stock -> lCMS.add(stock.getStatus()));
        if (lCMS.stream().anyMatch(x -> x.equals("Warning")) || lCMS.stream().anyMatch(x -> x.equals("Danger"))) {
            alarmLight = "Warning";
            if (lCMS.stream().anyMatch(x -> x.equals("Danger"))) {
                alarmLight = "Danger";
            }
        } else {
            alarmLight = "Ok";
        }
        return alarmLight;
    }
}
