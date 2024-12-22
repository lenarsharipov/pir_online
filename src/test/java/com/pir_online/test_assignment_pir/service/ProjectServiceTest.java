package com.pir_online.test_assignment_pir.service;

import com.pir_online.test_assignment_pir.dto.project.CreateProjectDto;
import com.pir_online.test_assignment_pir.dto.project.ReadProjectDto;
import com.pir_online.test_assignment_pir.mapper.ProjectMapper;
import com.pir_online.test_assignment_pir.model.Card;
import com.pir_online.test_assignment_pir.model.Project;
import com.pir_online.test_assignment_pir.model.Section;
import com.pir_online.test_assignment_pir.model.enums.Status;
import com.pir_online.test_assignment_pir.repository.CardRepository;
import com.pir_online.test_assignment_pir.repository.ProjectRepository;
import com.pir_online.test_assignment_pir.repository.SectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectService projectService;

    private static final Long PROJECT_ID = 1L;
    private static final Long CARD_ID = 2L;

    @Test
    void shouldFindByIdAndReturnMappedDto() {
        Project project = Project.builder().id(PROJECT_ID).build();
        ReadProjectDto expectedDto = createReadProjectDto();

        when(projectRepository.findById(PROJECT_ID)).thenReturn(Optional.of(project));
        when(projectMapper.toReadProjectDto(project)).thenReturn(expectedDto);

        ReadProjectDto result = projectService.findById(PROJECT_ID);

        assertEquals(expectedDto, result);
        verify(projectRepository).findById(PROJECT_ID);
        verify(projectMapper).toReadProjectDto(project);
    }

    @Test
    void shouldDeleteByIdAndRelatedCard() {
        Project project = createProjectWithCard();

        when(projectRepository.findById(PROJECT_ID)).thenReturn(Optional.of(project));

        projectService.deleteById(PROJECT_ID);

        verify(projectRepository).deleteByIdNative(PROJECT_ID);
        verify(cardRepository).deleteByIdNative(CARD_ID);
    }

    @Test
    void shouldCreateProjectAndReturnMappedDto() {
        CreateProjectDto createDto = createCreateProjectDto();
        Project project = Project.builder().build();
        ReadProjectDto expectedDto = createReadProjectDto();
        List<Section> sections = List.of(Section.builder().id(1L).build());

        when(projectMapper.toProjectEntity(createDto)).thenReturn(project);
        when(sectionRepository.findAllById(createDto.sections())).thenReturn(sections);
        when(projectMapper.toReadProjectDto(project)).thenReturn(expectedDto);

        ReadProjectDto result = projectService.create(createDto);

        assertEquals(expectedDto, result);
        verify(sectionRepository).findAllById(createDto.sections());
        verify(projectRepository).save(project);
        verify(projectMapper).toReadProjectDto(project);
    }

    private Project createProjectWithCard() {
        return Project.builder()
                .id(PROJECT_ID)
                .card(Card.builder().id(CARD_ID).build())
                .build();
    }

    private CreateProjectDto createCreateProjectDto() {
        return CreateProjectDto.builder()
                .projectName("project name")
                .projectCode("project code")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .sections(List.of(1L, 2L, 3L))
                .build();
    }

    private ReadProjectDto createReadProjectDto() {
        return ReadProjectDto.builder()
                .projectId(PROJECT_ID)
                .projectName("project name")
                .projectCode("project code")
                .status(Status.NEW)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .sections(List.of("section 1", "section 2"))
                .build();
    }
}