package com.pir_online.test_assignment_pir.validation;

import com.pir_online.test_assignment_pir.dto.project.CreateProjectDto;
import com.pir_online.test_assignment_pir.dto.project.UpdateProjectCardDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class EndDateAfterStartDateValidator
        implements ConstraintValidator<EndDateAfterStartDate, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj == null) {
            return true;
        }

        // Проверьте тип и приведите объект к нужному классу
        if (obj instanceof UpdateProjectCardDto dto) {
            return validateDates(dto.startDate(), dto.endDate(), context);
        } else if (obj instanceof CreateProjectDto createProjectDto) {
            return validateDates(createProjectDto.startDate(), createProjectDto.endDate(), context);
        }

        // Если объект неподдерживаемого типа
        throw new IllegalArgumentException("Unsupported type for @EndDateAfterStartDate");
    }

    private boolean validateDates(LocalDate startDate,
                                  LocalDate endDate,
                                  ConstraintValidatorContext context) {
        if (startDate == null || endDate == null) {
            return true;
        }

        if (!endDate.isAfter(startDate)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End date must be after start date")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
