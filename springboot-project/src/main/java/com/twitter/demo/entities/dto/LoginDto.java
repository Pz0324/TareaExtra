package com.twitter.demo.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class LoginDto {


    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
    }


