package com.pir_online.test_assignment_pir.service;

import com.pir_online.test_assignment_pir.dto.project.CreateProjectDto;
import com.pir_online.test_assignment_pir.dto.project.ReadProjectDto;
import com.pir_online.test_assignment_pir.dto.project.UpdateProjectCardDto;
import com.pir_online.test_assignment_pir.dto.project.UpdateProjectStatusDto;
import com.pir_online.test_assignment_pir.exception.ResourceNotFoundException;
import com.pir_online.test_assignment_pir.mapper.ProjectMapper;
import com.pir_online.test_assignment_pir.model.Card;
import com.pir_online.test_assignment_pir.model.Project;
import com.pir_online.test_assignment_pir.model.ProjectSection;
import com.pir_online.test_assignment_pir.model.Section;
import com.pir_online.test_assignment_pir.repository.CardRepository;
import com.pir_online.test_assignment_pir.repository.ProjectRepository;
import com.pir_online.test_assignment_pir.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CardRepository cardRepository;
    private final SectionRepository sectionRepository;
    private final ProjectMapper projectMapper;

    public ReadProjectDto findById(Long projectId) {
        Project project = getProject(projectId);
        return projectMapper.toReadProjectDto(project);
    }

    @Transactional
    public void deleteById(Long projectId) {
        Project project = getProject(projectId);
        Long cardId = project.getCard().getId();
        projectRepository.deleteByIdNative(projectId);
        cardRepository.deleteByIdNative(cardId);
    }

    @Transactional
    public ReadProjectDto create(CreateProjectDto dto) {
        Project project = projectMapper.toProjectEntity(dto);
        List<Section> usedSections = sectionRepository.findAllById(dto.sections());
        handleSections(usedSections, project);
        projectRepository.save(project);
        return projectMapper.toReadProjectDto(project);
    }

    @Transactional
    public ReadProjectDto updateProjectStatus(Long projectId, UpdateProjectStatusDto dto) {
        Project project = getProject(projectId);
        project.setStatus(dto.status());
        return projectMapper.toReadProjectDto(project);
    }

    @Transactional
    public ReadProjectDto updateProjectCard(Long projectId, UpdateProjectCardDto dto) {
        Project project = getProject(projectId);
        Card card = project.getCard();
        card.setProjectCode(dto.projectCode());
        card.setProjectName(dto.projectName());
        card.setStartDate(dto.startDate());
        card.setEndDate(dto.endDate());
        return projectMapper.toReadProjectDto(project);
    }

    @Transactional
    public ReadProjectDto addSection(Long projectId, Long sectionId) {
        Project project = getProject(projectId);
        Section section = getSection(sectionId);
        boolean sectionExists = project.getProjectSections().stream()
                .anyMatch(ps -> Objects.equals(ps.getSection().getId(), sectionId));

        if (!sectionExists) {
            addSectionToProject(project, section);
        }

        return projectMapper.toReadProjectDto(project);
    }

    private void addSectionToProject(Project project, Section section) {
        ProjectSection projectSection = ProjectSection.of(null, project, section);
        project.getProjectSections().add(projectSection);
    }

    @Transactional
    public ReadProjectDto deleteProjectSection(Long projectId, Long sectionId) {
        Project project = getProject(projectId);
        project.getProjectSections()
                .removeIf(p -> Objects.equals(p.getSection().getId(), sectionId));
        return projectMapper.toReadProjectDto(project);
    }

    private void handleSections(List<Section> usedSections, Project project) {
        List<ProjectSection> projectSections = usedSections.stream()
                .map(section -> ProjectSection.of(null, project, section))
                .toList();
        project.setProjectSections(projectSections);
    }

    private Section getSection(Long sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found"));
    }

    private Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }
}
