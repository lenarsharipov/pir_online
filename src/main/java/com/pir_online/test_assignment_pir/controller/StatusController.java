package com.pir_online.test_assignment_pir.controller;

import com.pir_online.test_assignment_pir.model.enums.Status;
import com.pir_online.test_assignment_pir.service.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statuses")
@AllArgsConstructor
@Tag(name = "Project Status Controller", description = "Status API")
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    @Operation(summary = "Get list of available project statuses")
    public List<Status> getAll() {
        return statusService.findAll();
    }
}
