package com.rozgaarsetu.rozgaarsetu.service;

import com.rozgaarsetu.rozgaarsetu.dto.LoginRequest;
import com.rozgaarsetu.rozgaarsetu.dto.RegisterRequest;
import com.rozgaarsetu.rozgaarsetu.dto.UserResponse;
import com.rozgaarsetu.rozgaarsetu.entity.Role;
import com.rozgaarsetu.rozgaarsetu.entity.User;
import com.rozgaarsetu.rozgaarsetu.entity.UserStatus;
import com.rozgaarsetu.rozgaarsetu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {
        if (request.getRole() == Role.ADMIN) {
            throw new RuntimeException("ADMIN registration not allowed");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already registered");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .password(request.getPassword()) // no hashing today
                .role(request.getRole())
                .status(UserStatus.ACTIVE)
                .build();

        User saved = userRepository.save(user);

        return UserResponse.builder()
                .id(saved.getId())
                .fullName(saved.getFullName())
                .phone(saved.getPhone())
                .role(saved.getRole())
                .status(saved.getStatus())
                .build();
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("Invalid phone or password"));

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new RuntimeException("User is blocked");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid phone or password");
        }

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
