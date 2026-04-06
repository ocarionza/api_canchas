package com.canchas.api.controller;

import com.canchas.api.dto.CanchaResponse;
import com.canchas.api.dto.HorarioResponse;
import com.canchas.api.service.CanchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Canchas")
@RestController
@RequestMapping("/api/canchas")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    @Operation(summary = "Listar canchas con filtros opcionales")
    @GetMapping
    public ResponseEntity<List<CanchaResponse>> listar(
            @RequestParam(required = false) Long sedeId,
            @RequestParam(required = false) Long tipoCanchaId) {
        return ResponseEntity.ok(canchaService.listar(sedeId, tipoCanchaId));
    }

    @Operation(summary = "Obtener detalle de una cancha")
    @GetMapping("/{id}")
    public ResponseEntity<CanchaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(canchaService.obtener(id));
    }

    @Operation(summary = "Horarios disponibles de una cancha para una fecha")
    @GetMapping("/{id}/horarios-disponibles")
    public ResponseEntity<List<HorarioResponse>> horariosDisponibles(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(canchaService.horariosDisponibles(id, fecha));
    }
}