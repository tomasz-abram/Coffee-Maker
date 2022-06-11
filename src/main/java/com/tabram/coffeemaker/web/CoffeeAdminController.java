package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CoffeeAdminController {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeAdminService coffeeAdminService;
    private final CoffeeUserService coffeeUserService;

    @Autowired
    public CoffeeAdminController(CoffeeAdminRepository coffeeAdminRepository, CoffeeAdminService coffeeAdminService, CoffeeUserService coffeeUserService) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.coffeeAdminService = coffeeAdminService;
        this.coffeeUserService = coffeeUserService;
    }

    @ModelAttribute("coffeeAdmin")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping("/admin/admin-add-coffee")
    public String showAddCoffeeForm() {
        return "admin/admin-add-coffee";
    }


    @PostMapping("/admin/admin-add-coffee")
    public String addCoffee(@ModelAttribute("coffeeAdmin") CoffeeDto coffeeDto) {
        coffeeAdminService.addNewCoffee(coffeeDto);
        coffeeUserService.addOneCoffeeForEachUser(coffeeDto);
        return "redirect:/admin/admin-coffee-list";
    }

    @GetMapping("/admin/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long coffeeAdminId) {
        ModelAndView mav = new ModelAndView("/admin/admin-add-coffee");
        CoffeeAdmin coffeeAdmin = coffeeAdminRepository.findById(coffeeAdminId).get();
        mav.addObject("coffeeAdmin", coffeeAdmin);
        return mav;
    }

    @GetMapping("admin/admin-coffee-list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("admin/admin-coffee-list");
        mav.addObject("coffees", coffeeAdminRepository.findAll());
        return mav;
    }

    @GetMapping("/admin/delete-admin-coffee")
    public String deleteAdminCoffee(@RequestParam Long coffeeAdminId) {
        coffeeAdminService.deleteCoffee(coffeeAdminId);
        return "redirect:/admin/admin-coffee-list";
    }
}

