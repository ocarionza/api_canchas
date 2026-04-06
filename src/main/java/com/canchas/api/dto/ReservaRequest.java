package com.canchas.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaRequest {

    @NotNull(message = "La cancha es obligatoria")
    private Long canchaId;

    @NotNull(message = "El horario es obligatorio")
    private Long horarioId;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    private LocalDate fecha;
}