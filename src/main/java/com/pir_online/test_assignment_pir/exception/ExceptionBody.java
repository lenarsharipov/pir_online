package com.pir_online.test_assignment_pir.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionBody {

    private String message;
    private Map<String, List<String>> errors;

    public ExceptionBody(String message) {
        this.message = message;
    }
}
