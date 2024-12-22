package com.pir_online.test_assignment_pir.dto.auth;

import lombok.Builder;

@Builder
public record JwtResponseDto(Long id,
                             String username,
                             String accessToken,
                             String refreshToken) {
}