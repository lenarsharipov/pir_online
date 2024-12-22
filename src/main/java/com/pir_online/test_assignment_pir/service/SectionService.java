package com.pir_online.test_assignment_pir.service;

import com.pir_online.test_assignment_pir.dto.project.ReadSectionDto;
import com.pir_online.test_assignment_pir.mapper.SectionMapper;
import com.pir_online.test_assignment_pir.model.Section;
import com.pir_online.test_assignment_pir.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    public List<ReadSectionDto> findAll() {
        List<Section> sections = sectionRepository.findAll();
        return sectionMapper.toReadDtoList(sections);
    }
}
