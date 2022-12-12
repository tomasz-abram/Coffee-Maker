package com.tabram.coffeemaker.api;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class CoffeeAdminController {

    private final CoffeeAdminService coffeeAdminService;

    public CoffeeAdminController(CoffeeAdminService coffeeAdminService) {
        this.coffeeAdminService = coffeeAdminService;
    }

    @GetMapping("/check-coffee-parameters")
    public ResponseEntity<String> checkCoffeeParameters(@RequestBody @Valid CoffeeDto coffeeDto) {
        return ResponseEntity.ok().body("You can make coffee");
    }

    @GetMapping("/coffees")
    public ResponseEntity<List<CoffeeAdmin>> getAllCoffee() {
        return ResponseEntity.ok().body(coffeeAdminService.getAllCoffees());
    }

    @GetMapping("/coffee/{coffeeName}")
    public ResponseEntity<CoffeeAdmin> findCoffeeAdminByCoffeeName(@PathVariable("coffeeName") String coffeeName) {
        return ResponseEntity.ok().body(coffeeAdminService.findCoffeeAdminByCoffeeName(coffeeName));
    }

    @DeleteMapping("/coffee/{coffeeName}")
    public ResponseEntity<String> deleteCoffeeAdmin(@PathVariable("coffeeName") String coffeeName) {
        coffeeAdminService.coffeeExists(coffeeName);
        coffeeAdminService.deleteCoffeeByCoffeeName(coffeeName);
        return ResponseEntity.ok().body("Success deleted admin coffee");
    }

    @PutMapping("/coffee")
    public ResponseEntity<CoffeeAdmin> updateAdminCoffee(@Valid @RequestBody CoffeeDto coffeeDto) {
        return ResponseEntity.ok().body(coffeeAdminService.updateAdminCoffee(coffeeDto));
    }

    @PostMapping("/coffee")
    public ResponseEntity<CoffeeAdmin> createCoffee(@Valid @RequestBody CoffeeDto coffeeDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/coffee").toUriString());
        return ResponseEntity.created(uri).body(coffeeAdminService.addNewCoffee(coffeeDto));
    }
}
