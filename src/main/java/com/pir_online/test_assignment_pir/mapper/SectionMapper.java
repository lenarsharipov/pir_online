package com.pir_online.test_assignment_pir.mapper;

import com.pir_online.test_assignment_pir.dto.project.ReadSectionDto;
import com.pir_online.test_assignment_pir.model.Section;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    List<ReadSectionDto> toReadDtoList(List<Section> sections);
}
