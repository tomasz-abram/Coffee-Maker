package com.tabram.coffeemaker.api;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/machine-constant")
public class CoffeeMachineConstantValueController {
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public CoffeeMachineConstantValueController(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @GetMapping("/min-water-container")
    public ResponseEntity<Integer> getMinWaterContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinWaterContainer());
    }
    @PutMapping("/min-water-container/{value}")
    public ResponseEntity<String> setMinWaterContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinWaterContainer();
        coffeeMachineConstantValueService.setMinWaterContainer(value);
        return ResponseEntity.ok().body("Min water container has been updated from " + before + " to " + value);
    }
    @GetMapping("/max-water-container")
    public ResponseEntity<Integer> getMaxWaterContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxWaterContainer());
    }
    @PutMapping("/max-water-container/{value}")
    public ResponseEntity<String> getMaxWaterContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxWaterContainer();
        coffeeMachineConstantValueService.setMaxWaterContainer(value);
        return ResponseEntity.ok().body("Max water container has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_milk_container")
    public ResponseEntity<Integer> getMinMilkContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinMilkContainer());
    }

    @PutMapping("/min_milk_container/{value}")
    public ResponseEntity<String> setMinMilkContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinMilkContainer();
        coffeeMachineConstantValueService.setMinMilkContainer(value);
        return ResponseEntity.ok().body("Min milk container has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_milk_container")
    public ResponseEntity<Integer> getMaxMilkContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxMilkContainer());
    }

    @PutMapping("/max_milk_container/{value}")
    public ResponseEntity<String> setMaxMilkContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxMilkContainer();
        coffeeMachineConstantValueService.setMaxMilkContainer(value);
        return ResponseEntity.ok().body("Max milk container has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_coffee_beans_container")
    public ResponseEntity<Integer> getMinCoffeeBeansContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinCoffeeBeansContainer());
    }

    @PutMapping("/min_coffee_beans_container/{value}")
    public ResponseEntity<String> setMinCoffeeBeansContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinCoffeeBeansContainer();
        coffeeMachineConstantValueService.setMinCoffeeBeansContainer(value);
        return ResponseEntity.ok().body("Min coffee beans container has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_coffee_beans_container")
    public ResponseEntity<Integer> getMaxCoffeeBeansContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxCoffeeBeansContainer());
    }

    @PutMapping("/max_coffee_beans_container/{value}")
    public ResponseEntity<String> setMaxCoffeeBeansContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxCoffeeBeansContainer();
        coffeeMachineConstantValueService.setMaxCoffeeBeansContainer(value);
        return ResponseEntity.ok().body("Max coffee beans container has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_ground_container")
    public ResponseEntity<Integer> getMinGroundContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinGroundContainer());
    }

    @PutMapping("/min_ground_container/{value}")
    public ResponseEntity<String> setMinGroundContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinGroundContainer();
        coffeeMachineConstantValueService.setMinGroundContainer(value);
        return ResponseEntity.ok().body("Min ground container has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_ground_container")
    public ResponseEntity<Integer> minMaxWaterContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxWaterContainer());
    }

    @PutMapping("/max_ground_container/{value}")
    public ResponseEntity<String> setMaxGroundContainer(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxGroundContainer();
        coffeeMachineConstantValueService.setMaxGroundContainer(value);
        return ResponseEntity.ok().body("Max ground container has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_descale_counter")
    public ResponseEntity<Integer> getMinDescaleCounter() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinDescaleCounter());
    }

    @PutMapping("/min_descale_counter/{value}")
    public ResponseEntity<String> setMinDescaleCounter(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinDescaleCounter();
        coffeeMachineConstantValueService.setMinDescaleCounter(value);
        return ResponseEntity.ok().body("Min descale counter has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_descale_counter")
    public ResponseEntity<Integer> getMaxDescaleCounter() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxDescaleCounter());
    }

    @PutMapping("/max_descale_counter/{value}")
    public ResponseEntity<String> setMaxDescaleCounter(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxDescaleCounter();
        coffeeMachineConstantValueService.setMaxDescaleCounter(value);
        return ResponseEntity.ok().body("Max descale counter has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_grinding_level")
    public ResponseEntity<Integer> getMaxGrindingLevel() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxGrindingLevel());
    }

    @PutMapping("/max_grinding_level/{value}")
    public ResponseEntity<String> setMaxGrindingLevel(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxGrindingLevel();
        coffeeMachineConstantValueService.setMaxGrindingLevel(value);
        return ResponseEntity.ok().body("Max grinding level has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_grinding_level")
    public ResponseEntity<Integer> getMinGrindingLevel() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinGrindingLevel());
    }

    @PutMapping("/min_grinding_level/{value}")
    public ResponseEntity<String> setMinGrindingLevel(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinGrindingLevel();
        coffeeMachineConstantValueService.setMinGrindingLevel(value);
        return ResponseEntity.ok().body("Min grinding level has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_amount_of_coffee")
    public ResponseEntity<Integer> getMaxAmountOfCoffee() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxAmountOfCoffee());
    }

    @PutMapping("/max_amount_of_coffee/{value}")
    public ResponseEntity<String> setMaxAmountOfCoffee(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxAmountOfCoffee();
        coffeeMachineConstantValueService.setMaxAmountOfCoffee(value);
        return ResponseEntity.ok().body("Max amount of coffee has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_amount_of_coffee")
    public ResponseEntity<Integer> getMinAmountOfCoffee() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinAmountOfCoffee());
    }

    @PutMapping("/min_amount_of_coffee/{value}")
    public ResponseEntity<String> setMinAmountOfCoffee(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinAmountOfCoffee();
        coffeeMachineConstantValueService.setMinAmountOfCoffee(value);
        return ResponseEntity.ok().body("Min amount of coffee has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_temp_water")
    public ResponseEntity<Integer> getMaxTempWater() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxTempWater());
    }

    @PutMapping("/max_temp_water/{value}")
    public ResponseEntity<String> setMaxTempWater(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxTempWater();
        coffeeMachineConstantValueService.setMaxTempWater(value);
        return ResponseEntity.ok().body("Max temp water has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_temp_water")
    public ResponseEntity<Integer> getMinTempWater() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinTempWater());
    }

    @PutMapping("/min_temp_water/{value}")
    public ResponseEntity<String> setMinTempWater(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinTempWater();
        coffeeMachineConstantValueService.setMinTempWater(value);
        return ResponseEntity.ok().body("Min temp water has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_amount_of_water")
    public ResponseEntity<Integer> getMaxAmountOfWater() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxAmountOfWater());
    }

    @PutMapping("/max_amount_of_water/{value}")
    public ResponseEntity<String> setMaxAmountOfWater(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxAmountOfWater();
        coffeeMachineConstantValueService.setMaxAmountOfWater(value);
        return ResponseEntity.ok().body("Max amount of water has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_amount_of_water")
    public ResponseEntity<Integer> getMinAmountOfWater() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinAmountOfWater());
    }

    @PutMapping("/min_amount_of_water/{value}")
    public ResponseEntity<String> setMinAmountOfWater(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinAmountOfWater();
        coffeeMachineConstantValueService.setMinAmountOfWater(value);
        return ResponseEntity.ok().body("Min amount of water has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_temp_milk")
    public ResponseEntity<Integer> getMaxTempMilk() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxTempMilk());
    }

    @PutMapping("/max_temp_milk/{value}")
    public ResponseEntity<String> setMaxTempMilk(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxTempMilk();
        coffeeMachineConstantValueService.setMaxTempMilk(value);
        return ResponseEntity.ok().body("Max temp milk has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_temp_milk")
    public ResponseEntity<Integer> getMinTempMilk() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinTempMilk());
    }

    @PutMapping("/min_temp_milk/{value}")
    public ResponseEntity<String> setMinTempMilk(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinTempMilk();
        coffeeMachineConstantValueService.setMinTempMilk(value);
        return ResponseEntity.ok().body("Min temp milk has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_amount_of_milk")
    public ResponseEntity<Integer> getMaxAmountOfMilk() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxAmountOfMilk());
    }

    @PutMapping("/max_amount_of_milk/{value}")
    public ResponseEntity<String> setMaxAmountOfMilk(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxAmountOfMilk();
        coffeeMachineConstantValueService.setMaxAmountOfMilk(value);
        return ResponseEntity.ok().body("Max amount of milk has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_amount_of_milk")
    public ResponseEntity<Integer> getMinAmountOfMilk() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinAmountOfMilk());
    }

    @PutMapping("/min_amount_of_milk/{value}")
    public ResponseEntity<String> setMinAmountOfMilk(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinAmountOfMilk();
        coffeeMachineConstantValueService.setMinAmountOfMilk(value);
        return ResponseEntity.ok().body("Min amount of milk has been updated from " + before + " to " + value);
    }

    @GetMapping("/max_cup_size")
    public ResponseEntity<Integer> getMaxCupSize() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMaxCupSize());
    }

    @PutMapping("/max_cup_size/{value}")
    public ResponseEntity<String> setMaxCupSize(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMaxCupSize();
        coffeeMachineConstantValueService.setMaxCupSize(value);
        return ResponseEntity.ok().body("Max cup size has been updated from " + before + " to " + value);
    }

    @GetMapping("/min_cup_size")
    public ResponseEntity<Integer> getMinCupSize() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getMinCupSize());
    }

    @PutMapping("/min_cup_size/{value}")
    public ResponseEntity<String> setMinCupSize(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getMinCupSize();
        coffeeMachineConstantValueService.setMinCupSize(value);
        return ResponseEntity.ok().body("Min cup size has been updated from " + before + " to " + value);
    }

    @GetMapping("/warning_level_water")
    public ResponseEntity<Integer> getWarningLevelWater() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getWarningLevelWater());
    }

    @PutMapping("/warning_level_water/{value}")
    public ResponseEntity<String> setWarningLevelWater(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getWarningLevelWater();
        coffeeMachineConstantValueService.setWarningLevelWater(value);
        return ResponseEntity.ok().body("Warning level water has been updated from " + before + " to " + value);
    }

    @GetMapping("/danger_level_water")
    public ResponseEntity<Integer> getDangerLevelWater() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getDangerLevelWater());
    }

    @PutMapping("/danger_level_water/{value}")
    public ResponseEntity<String> setDangerLevelWater(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getDangerLevelWater();
        coffeeMachineConstantValueService.setDangerLevelWater(value);
        return ResponseEntity.ok().body("Danger level water has been updated from " + before + " to " + value);
    }

    @GetMapping("/warning_level_milk")
    public ResponseEntity<Integer> getWarningLevelMilk() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getWarningLevelMilk());
    }

    @PutMapping("/warning_level_milk/{value}")
    public ResponseEntity<String> setWarningLevelMilk(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getWarningLevelMilk();
        coffeeMachineConstantValueService.setWarningLevelMilk(value);
        return ResponseEntity.ok().body("Warning level milk has been updated from " + before + " to " + value);
    }

    @GetMapping("/danger_level_milk")
    public ResponseEntity<Integer> getDangerLevelMilk() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getDangerLevelMilk());
    }

    @PutMapping("/danger_level_milk/{value}")
    public ResponseEntity<String> setDangerLevelMilk(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getDangerLevelMilk();
        coffeeMachineConstantValueService.setDangerLevelMilk(value);
        return ResponseEntity.ok().body("Danger level milk has been updated from " + before + " to " + value);
    }

    @GetMapping("/warning_level_coffee_beans")
    public ResponseEntity<Integer> getWarningLevelCoffeeBeans() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getWarningLevelCoffeeBeans());
    }

    @PutMapping("/warning_level_coffee_beans/{value}")
    public ResponseEntity<String> setWarningLevelCoffeeBeans(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getWarningLevelCoffeeBeans();
        coffeeMachineConstantValueService.setWarningLevelCoffeeBeans(value);
        return ResponseEntity.ok().body("Warning level coffee beans has been updated from " + before + " to " + value);
    }

    @GetMapping("/danger_level_coffee_beans")
    public ResponseEntity<Integer> getDangerLevelCoffeeBeans() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getDangerLevelCoffeeBeans());
    }

    @PutMapping("/danger_level_coffee_beans/{value}")
    public ResponseEntity<String> setDangerLevelCoffeeBeans(@PathVariable("value") int value) {
        Integer before = coffeeMachineConstantValueService.getDangerLevelCoffeeBeans();
        coffeeMachineConstantValueService.setDangerLevelCoffeeBeans(value);
        return ResponseEntity.ok().body("Danger level coffee beans has been updated from " + before + " to " + value);
    }

    @GetMapping("/warning_level_ground_container")
    public ResponseEntity<Integer> getWarningLevelGroundContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getWarningLevelGroundContainer());
    }

    @GetMapping("/danger_level_ground_container")
    public ResponseEntity<Integer> getDangerLevelGroundContainer() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getDangerLevelGroundContainer());
    }

    @GetMapping("/warning_level_descale")
    public ResponseEntity<Integer> getWarningLevelDescale() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getWarningLevelDescale());
    }

    @GetMapping("/danger_level_descale")
    public ResponseEntity<Integer> getDangerLevelDescale() {
        return ResponseEntity.ok().body(coffeeMachineConstantValueService.getDangerLevelDescale());
    }
}
