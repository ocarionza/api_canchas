package com.canchas.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String password;
}