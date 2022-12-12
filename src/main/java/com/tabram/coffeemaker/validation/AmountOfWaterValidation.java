package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountOfWaterValidation implements ConstraintValidator<AmountOfWater, Integer> {
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public AmountOfWaterValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public void initialize(AmountOfWater constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(integer, coffeeMachineConstantValueService.getMinAmountOfWater(), coffeeMachineConstantValueService.getMaxAmountOfWater())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinAmountOfWater())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxAmountOfWater())
                    .buildConstraintViolationWithTemplate("{error.range.amountOfWater}")
                    .addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }

    public boolean outOfRange(Integer val, int min, int max) {
        return val <= min || val >= max;
    }

}
