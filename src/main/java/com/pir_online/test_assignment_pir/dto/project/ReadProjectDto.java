package com.pir_online.test_assignment_pir.dto.project;

import com.pir_online.test_assignment_pir.model.enums.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ReadProjectDto(Long projectId,
                             Status status,
                             String projectName,
                             String projectCode,
                             LocalDate startDate,
                             LocalDate endDate,
                             List<String> sections) {
}
