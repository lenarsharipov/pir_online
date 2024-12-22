package com.pir_online.test_assignment_pir.dto.project;

import com.pir_online.test_assignment_pir.validation.EndDateAfterStartDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@EndDateAfterStartDate
public record UpdateProjectCardDto(

        @NotNull
        @Length(min = 1, max = 128)
        String projectName,

        @NotNull
        @Length(min = 1, max = 32)
        String projectCode,

        @NotNull
        @FutureOrPresent
        LocalDate startDate,

        @NotNull
        @Future
        LocalDate endDate
) {
}
