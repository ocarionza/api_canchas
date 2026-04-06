package com.canchas.api.dto;

import lombok.*;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class HorarioResponse {
    private Long id;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
