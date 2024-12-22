package com.pir_online.test_assignment_pir.mapper;

import com.pir_online.test_assignment_pir.dto.project.CreateProjectDto;
import com.pir_online.test_assignment_pir.dto.project.ReadProjectDto;
import com.pir_online.test_assignment_pir.model.Card;
import com.pir_online.test_assignment_pir.model.Project;
import com.pir_online.test_assignment_pir.model.ProjectSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "id", target = "projectId")
    @Mapping(source = "card.projectName", target = "projectName")
    @Mapping(source = "card.projectCode", target = "projectCode")
    @Mapping(source = "card.startDate", target = "startDate")
    @Mapping(source = "card.endDate", target = "endDate")
    @Mapping(target = "sections", expression = "java(mapSections(project.getProjectSections()))")
    ReadProjectDto toReadProjectDto(Project project);

    default List<String> mapSections(List<ProjectSection> projectSections) {
        return projectSections.stream()
                .map(ps -> ps.getSection().getType())
                .toList();
    }

    @Mapping(target = "card", source = "dto", qualifiedByName = "mapCard")
    Project toProjectEntity(CreateProjectDto dto);

    @Named("mapCard")
    default Card mapCard(CreateProjectDto dto) {
        return Card.builder()
                .projectCode(dto.projectCode())
                .projectName(dto.projectName())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
    }
}
