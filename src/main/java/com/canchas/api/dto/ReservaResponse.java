package com.canchas.api.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ReservaResponse {
    private Long id;
    private String cancha;
    private String sede;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
}