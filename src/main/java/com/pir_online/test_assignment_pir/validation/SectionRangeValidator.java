package com.pir_online.test_assignment_pir.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class SectionRangeValidator
        implements ConstraintValidator<SectionRange, List<Long>> {

    private long min;
    private long max;

    @Override
    public void initialize(SectionRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<Long> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.stream().allMatch(element -> element >= min && element <= max);
    }
}

