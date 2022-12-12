package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountOfCoffeeValidation implements ConstraintValidator<AmountOfCoffee, Double> {

    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public AmountOfCoffeeValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public boolean isValid(Double number, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(number, coffeeMachineConstantValueService.getMinAmountOfCoffee(), coffeeMachineConstantValueService.getMaxAmountOfCoffee())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinAmountOfCoffee())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxAmountOfCoffee())
                    .buildConstraintViolationWithTemplate("{error.range.amountOfCoffee}")
                    .addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void initialize(AmountOfCoffee constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    public boolean outOfRange(double val, int min, int max) {
        return val <= min || val >= max;
    }

}
