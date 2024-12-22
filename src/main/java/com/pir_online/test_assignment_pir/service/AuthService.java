package com.pir_online.test_assignment_pir.service;

import com.pir_online.test_assignment_pir.dto.auth.JwtRequestDto;
import com.pir_online.test_assignment_pir.dto.auth.JwtResponseDto;
import com.pir_online.test_assignment_pir.model.User;
import com.pir_online.test_assignment_pir.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider provider;
    private final UserService userService;

    public JwtResponseDto login(JwtRequestDto dto) {
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authenticationManager.authenticate(authenticationToken);
        User user = userService.getByUsername(dto.username());
        return JwtResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .accessToken(provider.createAccessToken(
                        user.getId(), user.getUsername(), Set.of(user.getRole())))
                .refreshToken(provider.createRefreshToken(
                        user.getId(), user.getUsername()))
                .build();
    }

    public JwtResponseDto refresh(String refreshToken) {
        return provider.refreshUserTokens(refreshToken);
    }
}
