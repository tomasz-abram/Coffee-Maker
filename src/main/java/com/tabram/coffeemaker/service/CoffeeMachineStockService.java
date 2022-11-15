package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeMachineStockService {


    private static final String STATUS_OK = "Ok";
    private static final String STATUS_WARNING = "Warning";
    private static final String STATUS_DANGER = "Danger";
    private final CoffeeMachineStockRepository coffeeMachineStockRepository;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;


    @Autowired
    public CoffeeMachineStockService(CoffeeMachineStockRepository coffeeMachineStockRepository, CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    public CoffeeMachineStock findStockByName(String stockName) {
        return coffeeMachineStockRepository.findByName(stockName);
    }

    public CoffeeMachineStock getWaterStock() {
        return coffeeMachineStockRepository.findByName("Water");
    }

    public CoffeeMachineStock getMilkStock() {
        return coffeeMachineStockRepository.findByName("Milk");
    }

    public CoffeeMachineStock getCoffeeBeansStock() {
        return coffeeMachineStockRepository.findByName("Coffee beans");
    }

    public CoffeeMachineStock getGroundContainerStock() {
        return coffeeMachineStockRepository.findByName("Ground container");
    }

    public CoffeeMachineStock getDescaleCounterStock() {
        return coffeeMachineStockRepository.findByName("Descale counter");
    }

    public CoffeeMachineStock getWaterHardnessStock() {
        return coffeeMachineStockRepository.findByName("Water hardness");
    }

    public void setStock(CoffeeMachineStock coffeeMachineStock) {
        coffeeMachineStockRepository.save(coffeeMachineStock);
    }


    public void updateWater(int addWater) {
        CoffeeMachineStock waterStock = getWaterStock();
        float updateWater = waterStock.getValue() + addWater;
        if (updateWater > coffeeMachineConstantValueService.getMaxWaterContainer()) {
            waterStock.setValue(coffeeMachineConstantValueService.getMaxWaterContainer());
        } else {
            waterStock.setValue(updateWater);
        }
        setStock(waterStock);
    }

    public void emptyWater() {
        CoffeeMachineStock waterStock = getWaterStock();
        waterStock.setValue(coffeeMachineConstantValueService.getMinWaterContainer());
        setStock(waterStock);
    }

    public void updateMilk(int addMilk) {
        CoffeeMachineStock milkStock = getMilkStock();
        float updateMilk = milkStock.getValue() + addMilk;
        if (updateMilk > coffeeMachineConstantValueService.getMaxMilkContainer()) {
            milkStock.setValue(coffeeMachineConstantValueService.getMaxMilkContainer());
        } else {
            milkStock.setValue(updateMilk);
        }
        setStock(milkStock);
    }

    public void emptyMilk() {
        CoffeeMachineStock milkStock = getMilkStock();
        milkStock.setValue(coffeeMachineConstantValueService.getMinMilkContainer());
        setStock(milkStock);
    }

    public void updateBeans(int addBeans) {
        CoffeeMachineStock beansStock = getCoffeeBeansStock();
        float updateBeans = beansStock.getValue() + addBeans;
        if (updateBeans > coffeeMachineConstantValueService.getMaxCoffeeBeansContainer()) {
            beansStock.setValue(coffeeMachineConstantValueService.getMaxCoffeeBeansContainer());
        } else {
            beansStock.setValue(updateBeans);
        }
        setStock(beansStock);
    }

    public void emptyCoffeeBeans() {
        CoffeeMachineStock beansStock = getCoffeeBeansStock();
        beansStock.setValue(coffeeMachineConstantValueService.getMinCoffeeBeansContainer());
        setStock(beansStock);
    }

    public void emptyGroundContainer() {
        CoffeeMachineStock groundContainerStock = getGroundContainerStock();
        groundContainerStock.setValue(coffeeMachineConstantValueService.getMinGroundContainer());
        setStock(groundContainerStock);
    }

    public void descale() {
        CoffeeMachineStock descaleStock = coffeeMachineStockRepository.findByName("Descale counter");
        descaleStock.setValue(coffeeMachineConstantValueService.getMinDescaleCounter());
        setStock(descaleStock);
    }

    public void updateWaterHardness(float setHardness) {
        CoffeeMachineStock waterHardnessStock = getWaterHardnessStock();
        if (setHardness < 0) {
            throw new IllegalArgumentException("The water hardness must not be less than zero");
        }
        if (setHardness > 100) {
            throw new IllegalArgumentException("If you don't pour concrete over your coffee, this value is probably lower :)");
        }
        waterHardnessStock.setValue(setHardness);
        setStock(waterHardnessStock);
    }

    public void checkStockStatus() {
        checkStockStatusForWater();
        checkStockStatusForMilk();
        checkStockStatusForCoffeeBeans();
        checkStockStatusForGroundContainer();
        checkStockStatusForDescaleCounter();
    }

    public void checkStockStatusForWater() {
        CoffeeMachineStock waterStock = getWaterStock();
        if (waterStock.getValue() > coffeeMachineConstantValueService.getWarningLevelWater()) {
            waterStock.setStatus(STATUS_OK);
        } else if (waterStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelWater()) {
            waterStock.setStatus(STATUS_WARNING);
            if (waterStock.getValue() <= coffeeMachineConstantValueService.getDangerLevelWater()) {
                waterStock.setStatus(STATUS_DANGER);
            }
        }
        setStock(waterStock);
    }

    public void checkStockStatusForMilk() {
        CoffeeMachineStock milkStock = getMilkStock();
        if (milkStock.getValue() > coffeeMachineConstantValueService.getWarningLevelMilk()) {
            milkStock.setStatus(STATUS_OK);
        } else if (milkStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelMilk()) {
            milkStock.setStatus(STATUS_WARNING);
            if (milkStock.getValue() <= coffeeMachineConstantValueService.getDangerLevelMilk()) {
                milkStock.setStatus(STATUS_DANGER);
            }
        }
        setStock(milkStock);
    }

    public void checkStockStatusForCoffeeBeans() {
        CoffeeMachineStock coffeeBeansStock = getCoffeeBeansStock();
        if (coffeeBeansStock.getValue() > coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()) {
            coffeeBeansStock.setStatus(STATUS_OK);
        } else if (coffeeBeansStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelCoffeeBeans()) {
            coffeeBeansStock.setStatus(STATUS_WARNING);
            if (coffeeBeansStock.getValue() <= coffeeMachineConstantValueService.getDangerLevelCoffeeBeans()) {
                coffeeBeansStock.setStatus(STATUS_DANGER);
            }
        }
        setStock(coffeeBeansStock);
    }

    public void checkStockStatusForGroundContainer() {
        CoffeeMachineStock groundContainerStock = getGroundContainerStock();
        if (groundContainerStock.getValue() <= coffeeMachineConstantValueService.getWarningLevelGroundContainer()) {
            groundContainerStock.setStatus(STATUS_OK);
        } else if (groundContainerStock.getValue() >= coffeeMachineConstantValueService.getWarningLevelGroundContainer()) {
            groundContainerStock.setStatus(STATUS_WARNING);
            if (groundContainerStock.getValue() >= coffeeMachineConstantValueService.getDangerLevelGroundContainer()) {
                groundContainerStock.setStatus(STATUS_DANGER);
            }
        }
        setStock(groundContainerStock);

    }

    public void checkStockStatusForDescaleCounter() {
        CoffeeMachineStock descaleCounter = getDescaleCounterStock();
        if (descaleCounter.getValue() < coffeeMachineConstantValueService.getWarningLevelDescale()) {
            descaleCounter.setStatus(STATUS_OK);
        } else if (descaleCounter.getValue() >= coffeeMachineConstantValueService.getWarningLevelDescale()) {
            descaleCounter.setStatus(STATUS_WARNING);
            if (descaleCounter.getValue() >= coffeeMachineConstantValueService.getDangerLevelDescale()) {
                descaleCounter.setStatus(STATUS_DANGER);
            }
        }
        setStock(descaleCounter);
    }

    public String alarmLightStockStatus() {
        String alarmLight;
        List<String> alarmLightCoffeeMachineStock = new ArrayList<>();
        coffeeMachineStockRepository.findAll().forEach(stock -> alarmLightCoffeeMachineStock.add(stock.getStatus()));
        if (alarmLightCoffeeMachineStock.stream().anyMatch(x -> x.equals(STATUS_WARNING)) || alarmLightCoffeeMachineStock.stream().anyMatch(x -> x.equals(STATUS_DANGER))) {
            alarmLight = STATUS_WARNING;
            if (alarmLightCoffeeMachineStock.stream().anyMatch(x -> x.equals(STATUS_DANGER))) {
                alarmLight = STATUS_DANGER;
            }
        } else {
            alarmLight = STATUS_OK;
        }
        return alarmLight;
    }
}
