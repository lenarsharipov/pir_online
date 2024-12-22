package com.pir_online.test_assignment_pir.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

/**
 * Модель карточки проекта, содержащая основную его информацию:
 * Название проекта,
 * Шифр проекта,
 * Дата начала проекта,
 * Дата окончания проект
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String projectCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
