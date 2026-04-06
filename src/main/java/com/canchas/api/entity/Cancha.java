package com.canchas.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "canchas")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;
    private Integer capacidad;
    private String imagenUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cancha_id", nullable = false)
    private TipoCancha tipoCancha;
}