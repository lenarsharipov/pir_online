package com.pir_online.test_assignment_pir.dto;

import com.pir_online.test_assignment_pir.dto.project.CreateProjectDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateProjectDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCreateProjectDto() {
        // Arrange
        CreateProjectDto dto = new CreateProjectDto(
                "Project Alpha",
                "PA-001",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                List.of(1L, 2L)
        );

        // Act
        Set<ConstraintViolation<CreateProjectDto>> violations = validator.validate(dto);

        // Assert
        assertTrue(violations.isEmpty(), "Expected no violations for valid DTO");
    }

    @Test
    void testInvalidProjectName() {
        // Arrange
        CreateProjectDto dto = new CreateProjectDto(
                "",
                "PA-001",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                List.of(1L)
        );

        // Act
        Set<ConstraintViolation<CreateProjectDto>> violations = validator.validate(dto);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(
                v -> v.getPropertyPath().toString().equals("projectName")
        ));
    }

    @Test
    void testEndDateBeforeStartDate() {
        // Arrange
        CreateProjectDto dto = new CreateProjectDto(
                "Project Alpha",
                "PA-001",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(1),
                List.of(1L)
        );

        // Act
        Set<ConstraintViolation<CreateProjectDto>> violations = validator.validate(dto);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(
                v -> v.getMessage().equals("End date must be after start date")
        ));
    }

    @Test
    void testEmptySectionIds() {
        // Arrange
        CreateProjectDto dto = new CreateProjectDto(
                "Project Alpha",
                "PA-001",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                List.of()
        );

        // Act
        Set<ConstraintViolation<CreateProjectDto>> violations = validator.validate(dto);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(
                v -> v.getPropertyPath().toString().equals("sections")
        ));
    }

    @Test
    void testDuplicateSectionIds() {
        // Arrange
        CreateProjectDto dto = new CreateProjectDto(
                "Project Alpha",
                "PA-001",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                List.of(1L, 1L)
        );

        // Act
        Set<ConstraintViolation<CreateProjectDto>> violations = validator.validate(dto);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(
                v -> v.getPropertyPath().toString().equals("sections")
        ));
    }
}
