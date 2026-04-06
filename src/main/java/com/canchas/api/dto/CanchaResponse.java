package com.canchas.api.dto;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CanchaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer capacidad;
    private String imagenUrl;
    private String sede;
    private Long sedeId;
    private String tipoCancha;
    private Long tipoCanchaId;
}