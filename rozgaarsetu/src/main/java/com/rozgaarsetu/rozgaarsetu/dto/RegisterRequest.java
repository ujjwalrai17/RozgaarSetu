package com.rozgaarsetu.rozgaarsetu.dto;

import com.rozgaarsetu.rozgaarsetu.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(min=3, max=60)
    private String fullName;

    @NotBlank
    @Pattern(regexp ="^[6-9]\\d{9}$", message = "Phone must be a valid 10-digit Indian mobile number")
    private String phone;

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    @NotNull
    private Role role;
}
