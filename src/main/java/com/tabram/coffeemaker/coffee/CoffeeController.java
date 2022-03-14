package com.tabram.coffeemaker.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CoffeeController {

    private CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }


    @GetMapping
    public List<Coffee> getCoffees() {
        return coffeeService.getCoffees();
    }

    @PostMapping
    public void registerNewCoffee(@RequestBody Coffee coffee) {
        coffeeService.addCoffee(coffee);
    }

    @DeleteMapping(path = {"coffeeId"})
    public void deleteCoffee(@PathVariable("coffeeId") Long coffeeId){
        coffeeService.deleteCoffee(coffeeId);
    }

    @PutMapping(path = {"coffeeId"})
    public void updateCoffee(
        @PathVariable("coffeeId") Long coffeeId,
        @RequestParam(required = false) String nameOfCoffee){
    coffeeService.updateCoffee(coffeeId,nameOfCoffee);
    }
    {
    }
}