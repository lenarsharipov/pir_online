package com.pir_online.test_assignment_pir.controller;

import com.pir_online.test_assignment_pir.dto.project.ReadSectionDto;
import com.pir_online.test_assignment_pir.service.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sections")
@AllArgsConstructor
@Tag(name = "Section Controller", description = "Section API")
public class SectionController {

    private final SectionService sectionService;

    @GetMapping
    @Operation(summary = "Get list of available project sections")
    public List<ReadSectionDto> getAllSections() {
        return sectionService.findAll();
    }
}
