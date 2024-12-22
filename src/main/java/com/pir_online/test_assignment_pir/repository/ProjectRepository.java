package com.pir_online.test_assignment_pir.repository;

import com.pir_online.test_assignment_pir.model.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @NonNull
    @EntityGraph(attributePaths = {"card", "projectSections.section"})
    Optional<Project> findById(@NonNull Long id);

    @Modifying
    @Query(value = "DELETE FROM project WHERE id = :projectId", nativeQuery = true)
    void deleteByIdNative(@Param("projectId") Long projectId);
}
