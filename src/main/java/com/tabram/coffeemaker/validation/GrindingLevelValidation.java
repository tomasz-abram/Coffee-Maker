package com.tabram.coffeemaker.validation;

import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GrindingLevelValidation implements ConstraintValidator<GrindingLevel, Integer> {
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    public GrindingLevelValidation(CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @Override
    public void initialize(GrindingLevel constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (outOfRange(integer, coffeeMachineConstantValueService.getMinGrindingLevel(), coffeeMachineConstantValueService.getMaxGrindingLevel())) {
            HibernateConstraintValidatorContext hibernateConstraintValidatorContext = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
            hibernateConstraintValidatorContext.disableDefaultConstraintViolation();
            hibernateConstraintValidatorContext
                    .addMessageParameter("min", coffeeMachineConstantValueService.getMinGrindingLevel())
                    .addMessageParameter("max", coffeeMachineConstantValueService.getMaxGrindingLevel())
                    .buildConstraintViolationWithTemplate("{error.range.grindingLevel}")
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
