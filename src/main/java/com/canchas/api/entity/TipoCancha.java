package com.canchas.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_cancha")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TipoCancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // "Fútbol", "Tenis", etc.
}