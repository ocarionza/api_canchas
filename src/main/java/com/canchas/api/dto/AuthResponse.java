package com.canchas.api.dto;

import lombok.*;

@Data @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String nombre;
    private String correo;
}