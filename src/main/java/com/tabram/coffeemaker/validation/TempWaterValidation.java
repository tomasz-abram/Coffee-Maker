package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TempWaterValidation implements ConstraintValidator<TempWater, Integer> {
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public TempWaterValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public void initialize(TempWater constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(integer, coffeeMachineConstantValueService.getMinTempWater(), coffeeMachineConstantValueService.getMaxTempWater())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinTempWater())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxTempWater())
                    .buildConstraintViolationWithTemplate("{error.range.tempWater}")
                    .addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
    public boolean outOfRange(double val, int min, int max) {
        return val <= min || val >= max;
    }

}
