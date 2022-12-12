package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CupSizeValidation implements ConstraintValidator<CupSize, Integer> {

    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public CupSizeValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public void initialize(CupSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(integer, coffeeMachineConstantValueService.getMinCupSize(), coffeeMachineConstantValueService.getMaxCupSize())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinCupSize())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxCupSize())
                    .buildConstraintViolationWithTemplate("{error.range.cupSize}")
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
