package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountOfMilkValidation implements ConstraintValidator<AmountOfMilk, Integer> {

    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public AmountOfMilkValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }


    @Override
    public void initialize(AmountOfMilk constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(integer, coffeeMachineConstantValueService.getMinAmountOfMilk(), coffeeMachineConstantValueService.getMaxAmountOfMilk())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinAmountOfMilk())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxAmountOfMilk())
                    .buildConstraintViolationWithTemplate("{error.range.amountOfMilk}")
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
