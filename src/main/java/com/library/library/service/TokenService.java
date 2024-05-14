package com.library.library.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.library.library.config.jwt.TokenProvider;
import com.library.library.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }
        Long UserId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(UserId);
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
