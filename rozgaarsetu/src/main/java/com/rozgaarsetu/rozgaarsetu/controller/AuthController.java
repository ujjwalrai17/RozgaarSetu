package com.rozgaarsetu.rozgaarsetu.controller;

import com.rozgaarsetu.rozgaarsetu.dto.LoginRequest;
import com.rozgaarsetu.rozgaarsetu.dto.RegisterRequest;
import com.rozgaarsetu.rozgaarsetu.dto.UserResponse;
import com.rozgaarsetu.rozgaarsetu.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
