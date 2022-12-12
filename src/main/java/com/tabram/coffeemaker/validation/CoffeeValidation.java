package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoffeeValidation implements ConstraintValidator<Coffee, CoffeeDto> {

    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public CoffeeValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public void initialize(Coffee constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CoffeeDto coffeeDto, ConstraintValidatorContext constraintValidatorContext) {
        boolean coffeeIsOk = true;
        if (coffeeDto.getCoffeeName().isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The coffee name can not be empty.").addConstraintViolation();
            coffeeIsOk = false;
        }
        if (outOfRange(coffeeDto.getTempWater(), coffeeMachineConstantValueService.getMinTempWater(), coffeeMachineConstantValueService.getMaxTempWater())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The water temperature must be in the range " + coffeeMachineConstantValueService.getMinTempWater() + " - " + coffeeMachineConstantValueService.getMaxTempWater()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (outOfRange(coffeeDto.getGrindingLevel(), coffeeMachineConstantValueService.getMinGrindingLevel(), coffeeMachineConstantValueService.getMaxGrindingLevel())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The grinding level must be in the range " + coffeeMachineConstantValueService.getMinGrindingLevel() + " - " + coffeeMachineConstantValueService.getMaxGrindingLevel()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (outOfRange(coffeeDto.getAmountOfCoffee(), coffeeMachineConstantValueService.getMinAmountOfCoffee(), coffeeMachineConstantValueService.getMaxAmountOfCoffee())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The amount of coffee must be in the range " + coffeeMachineConstantValueService.getMinAmountOfCoffee() + " - " + coffeeMachineConstantValueService.getMaxAmountOfCoffee()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (outOfRange(coffeeDto.getAmountOfWater(), coffeeMachineConstantValueService.getMinAmountOfWater(), coffeeMachineConstantValueService.getMaxAmountOfWater())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The amount of water must be in the range " + coffeeMachineConstantValueService.getMinAmountOfWater() + " - " + coffeeMachineConstantValueService.getMaxAmountOfWater()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (outOfRange(coffeeDto.getAmountMilk(), coffeeMachineConstantValueService.getMinAmountOfMilk(), coffeeMachineConstantValueService.getMaxAmountOfMilk())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The amount milk must be in the range " + coffeeMachineConstantValueService.getMinAmountOfMilk() + " - " + coffeeMachineConstantValueService.getMaxAmountOfMilk()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (coffeeDto.getAmountMilk() != 0 && outOfRange(coffeeDto.getTempMilk(), coffeeMachineConstantValueService.getMinTempMilk(), coffeeMachineConstantValueService.getMaxTempMilk())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The temp. milk must be in the range " + coffeeMachineConstantValueService.getMinTempMilk() + " - " + coffeeMachineConstantValueService.getMaxTempMilk()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (outOfRange(coffeeDto.getCupSize(), coffeeMachineConstantValueService.getMinCupSize(), coffeeMachineConstantValueService.getMaxCupSize())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The cup size must be in the range " + coffeeMachineConstantValueService.getMinCupSize() + " - " + coffeeMachineConstantValueService.getMaxCupSize()).addConstraintViolation();
            coffeeIsOk = false;
        }
        if (coffeeDto.getAmountOfWater() + coffeeDto.getAmountMilk() > coffeeDto.getCupSize()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The cup must not be smaller than the sum of the amounts of water and milk.");
            coffeeIsOk = false;
        }

        return coffeeIsOk;
    }

    public boolean outOfRange(double val, int min, int max) {
        return val <= min || val >= max;
    }
}
