package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TempMilkValidation  implements ConstraintValidator<TempMilk, Integer> {
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public TempMilkValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public void initialize(TempMilk constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(integer, coffeeMachineConstantValueService.getMinTempMilk(), coffeeMachineConstantValueService.getMaxTempMilk())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinTempMilk())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxTempMilk())
                    .buildConstraintViolationWithTemplate("{error.range.tempMilk}")
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
