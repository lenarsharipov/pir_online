package com.pir_online.test_assignment_pir.dto.project;

import com.pir_online.test_assignment_pir.model.enums.Status;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectStatusDto(@NotNull Status status) {
}
