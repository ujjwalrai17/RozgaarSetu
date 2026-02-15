package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.LoginRequest;
import com.rozgaarsetu.rozgaarsetu.dto.RegisterRequest;
import com.rozgaarsetu.rozgaarsetu.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);
    UserResponse login(LoginRequest request);
}
