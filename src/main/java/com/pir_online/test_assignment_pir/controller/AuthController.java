package com.pir_online.test_assignment_pir.controller;

import com.pir_online.test_assignment_pir.dto.auth.JwtRequestDto;
import com.pir_online.test_assignment_pir.dto.auth.JwtResponseDto;
import com.pir_online.test_assignment_pir.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Log in by username and password")
    public JwtResponseDto login(@Valid @RequestBody JwtRequestDto dto) {
        return authService.login(dto);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token by username and password")
    public JwtResponseDto refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
