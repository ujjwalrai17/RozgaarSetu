package com.rozgaarsetu.rozgaarsetu.dto;

import com.rozgaarsetu.rozgaarsetu.entity.Role;
import com.rozgaarsetu.rozgaarsetu.entity.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String fullName;
    private String phone;
    private Role role;
    private UserStatus status;
}
