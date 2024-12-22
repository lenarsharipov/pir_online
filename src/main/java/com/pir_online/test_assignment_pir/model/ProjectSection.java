package com.pir_online.test_assignment_pir.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(of = "id", callSuper=false)
@Builder
@Data
@Entity
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "project_sections")
public class ProjectSection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
}
