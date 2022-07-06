package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.config.CoffeeMachine;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class CoffeeMachineStockService {


    private final CoffeeMachineStockRepository coffeeMachineStockRepository;
    private final CoffeeMachine coffeeMachine;

    @Autowired
    public CoffeeMachineStockService(CoffeeMachineStockRepository coffeeMachineStockRepository, CoffeeMachine coffeeMachine) {
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
        this.coffeeMachine = coffeeMachine;
    }


    public void updateWater(int addWater) {
        CoffeeMachineStock waterStock = coffeeMachineStockRepository.findByName("Water");
        float updateWater = (Objects.requireNonNull(waterStock).getValue() + addWater);
        if (updateWater > coffeeMachine.getMAX_WATER_CONTAINER()) {
            waterStock.setValue(coffeeMachine.getMAX_WATER_CONTAINER());
        } else {
            waterStock.setValue(updateWater);
        }
        coffeeMachineStockRepository.save(waterStock);
    }

    public void emptyWater() {
        CoffeeMachineStock waterStock = coffeeMachineStockRepository.findByName("Water");
        Objects.requireNonNull(waterStock).setValue(0);
        coffeeMachineStockRepository.save(waterStock);
    }


    public void updateMilk(int addMilk) {
        CoffeeMachineStock milkStock = coffeeMachineStockRepository.findByName("Milk");
        float updateMilk = (Objects.requireNonNull(milkStock).getValue() + addMilk);

        if (updateMilk > coffeeMachine.getMAX_MILK_CONTAINER()) {
            milkStock.setValue(coffeeMachine.getMAX_MILK_CONTAINER());
        } else {
            milkStock.setValue(updateMilk);
        }
        coffeeMachineStockRepository.save(milkStock);
    }

    public void emptyMilk() {
        CoffeeMachineStock milkStock = coffeeMachineStockRepository.findByName("Milk");
        Objects.requireNonNull(milkStock).setValue(0);
        coffeeMachineStockRepository.save(milkStock);
    }

    public void updateBeans(int addBeans) {
        CoffeeMachineStock beansStock = coffeeMachineStockRepository.findByName("Coffee beans");
        float updateBeans = Objects.requireNonNull(beansStock).getValue() + addBeans;

        if (updateBeans > coffeeMachine.getMAX_COFFEE_BEANS_CONTAINER()) {
            beansStock.setValue(coffeeMachine.getMAX_COFFEE_BEANS_CONTAINER());
        } else {
            beansStock.setValue(updateBeans);
        }
        coffeeMachineStockRepository.save(beansStock);
    }

    public void emptyCoffeeBeans() {
        CoffeeMachineStock beansStock = coffeeMachineStockRepository.findByName("Coffee beans");
        Objects.requireNonNull(beansStock).setValue(0);
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

        if (waterStock.getValue() > coffeeMachine.getWARNING_LEVEL_WATER()) {
            waterStock.setStatus("Ok");
        } else if (waterStock.getValue() <= coffeeMachine.getWARNING_LEVEL_WATER()) {
            waterStock.setStatus("Warning");
            if (waterStock.getValue() <= coffeeMachine.getDANGER_LEVEL_WATER()) {
                waterStock.setStatus("Danger");
            }
        }

        if (milkStock.getValue() > coffeeMachine.getWARNING_LEVEL_MILK()) {
            milkStock.setStatus("Ok");
        } else if (milkStock.getValue() <= coffeeMachine.getWARNING_LEVEL_MILK()) {
            milkStock.setStatus("Warning");
            if (milkStock.getValue() <= coffeeMachine.getDANGER_LEVEL_MILK()) {
                milkStock.setStatus("Danger");
            }
        }

        if (coffeeBeansStock.getValue() > coffeeMachine.getWARNING_LEVEL_COFFEE_BEANS()) {
            coffeeBeansStock.setStatus("Ok");
        } else if (coffeeBeansStock.getValue() <= coffeeMachine.getWARNING_LEVEL_COFFEE_BEANS()) {
            coffeeBeansStock.setStatus("Warning");
            if (coffeeBeansStock.getValue() <= coffeeMachine.getDANGER_LEVEL_COFFEE_BEANS()){
                coffeeBeansStock.setStatus("Danger");
            }
        }

        if (groundContainerStock.getValue() <= coffeeMachine.getWARNING_LEVEL_GROUND_CONTAINER()) {
            groundContainerStock.setStatus("Ok");
        } else if (groundContainerStock.getValue() >= coffeeMachine.getWARNING_LEVEL_GROUND_CONTAINER()) {
            groundContainerStock.setStatus("Warning");
            if (groundContainerStock.getValue() >= coffeeMachine.getDANGER_LEVEL_GROUND_CONTAINER()) {
                groundContainerStock.setStatus("Danger");
            }
        }

        if (descaleCounter.getValue() < coffeeMachine.getWARNING_LEVEL_DESCALE()) {
            descaleCounter.setStatus("Ok");
        } else if (descaleCounter.getValue() >= coffeeMachine.getWARNING_LEVEL_DESCALE()) {
            descaleCounter.setStatus("Warning");
            if (descaleCounter.getValue() >= coffeeMachine.getDANGER_LEVEL_DESCALE()) {
                descaleCounter.setStatus("Danger");
            }
        }

        List<CoffeeMachineStock> stocks = List.of(waterStock,milkStock,coffeeBeansStock,groundContainerStock,descaleCounter);
        coffeeMachineStockRepository.saveAll(stocks);
    }
    public String alarmLightStockStatus(){

        String alarmLight;
        List<String> lCMS = new ArrayList<>();
        coffeeMachineStockRepository.findAll().forEach(stock -> lCMS.add(stock.getStatus()));

        if (lCMS.stream().anyMatch(x->x.equals("Warning")) || lCMS.stream().anyMatch(x->x.equals("Danger"))){
            alarmLight = "Warning";
            if (lCMS.stream().anyMatch(x->x.equals("Danger"))){
                alarmLight = "Danger";
            }
        } else {
            alarmLight = "Ok";
        }

   return alarmLight;
    }
}
