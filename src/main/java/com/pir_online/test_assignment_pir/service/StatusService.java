package com.pir_online.test_assignment_pir.service;

import com.pir_online.test_assignment_pir.model.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    public List<Status> findAll() {
        return List.of(Status.values());
    }
}
