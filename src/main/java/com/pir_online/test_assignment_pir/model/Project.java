package com.pir_online.test_assignment_pir.model;

import com.pir_online.test_assignment_pir.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@EqualsAndHashCode(of = "id", callSuper = true)
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY, cascade = ALL, optional = false)
    private Card card;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.NEW;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<ProjectSection> projectSections = new ArrayList<>();
}
