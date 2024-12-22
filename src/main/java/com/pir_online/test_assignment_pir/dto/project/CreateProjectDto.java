package com.pir_online.test_assignment_pir.dto.project;

import com.pir_online.test_assignment_pir.validation.EndDateAfterStartDate;
import com.pir_online.test_assignment_pir.validation.SectionRange;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.List;

@EndDateAfterStartDate
@Builder
public record CreateProjectDto(

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
        LocalDate endDate,

        @NotNull
        @Size(min = 1)
        @UniqueElements
        @SectionRange(max = 1_000_000)
        List<Long> sections
) {
}
