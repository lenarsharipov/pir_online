package com.pir_online.test_assignment_pir.controller;

import com.pir_online.test_assignment_pir.dto.project.CreateProjectDto;
import com.pir_online.test_assignment_pir.dto.project.ReadProjectDto;
import com.pir_online.test_assignment_pir.dto.project.UpdateProjectCardDto;
import com.pir_online.test_assignment_pir.dto.project.UpdateProjectStatusDto;
import com.pir_online.test_assignment_pir.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
@Tag(name = "Project Controller", description = "Project API")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{projectId}")
    @Operation(summary = "Get project by id")
    public ReadProjectDto getProject(@PathVariable Long projectId) {
        return projectService.findById(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create project by passing valid dto")
    public ReadProjectDto createProject(@Valid @RequestBody CreateProjectDto dto) {
        return projectService.create(dto);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete project by id. Related card will be deleted as well")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteById(projectId);
    }

    @PutMapping("/{projectId}/status")
    @Operation(summary = "Update project status by passing valid new status")
    public ReadProjectDto updateProjectStatus(@PathVariable Long projectId,
                                              @Valid @RequestBody UpdateProjectStatusDto dto) {
        return projectService.updateProjectStatus(projectId, dto);
    }

    @PutMapping("/{projectId}/card")
    @Operation(summary = "Update project card info")
    public ReadProjectDto updateProjectCardData(@PathVariable Long projectId,
                                                @Valid @RequestBody UpdateProjectCardDto dto) {
        return projectService.updateProjectCard(projectId, dto);
    }

    @PostMapping("/{projectId}/sections/{sectionId}")
    @Operation(summary = "Add additional section to the project")
    public ReadProjectDto addProjectSection(@PathVariable Long projectId,
                                            @PathVariable Long sectionId) {
        return projectService.addSection(projectId, sectionId);
    }

    @DeleteMapping("/{projectId}/sections/{sectionId}")
    @Operation(summary = "Delete project section of the project")
    public ReadProjectDto deleteProjectSection(@PathVariable Long projectId,
                                               @PathVariable Long sectionId) {
        return projectService.deleteProjectSection(projectId, sectionId);
    }
}
