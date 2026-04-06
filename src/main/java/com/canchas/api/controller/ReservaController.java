package com.canchas.api.controller;

import com.canchas.api.dto.*;
import com.canchas.api.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservas")
@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @Operation(summary = "Crear una reserva")
    @PostMapping
    public ResponseEntity<ReservaResponse> crear(
            @Valid @RequestBody ReservaRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(reservaService.crear(request, userDetails.getUsername()));
    }

    @Operation(summary = "Ver mis reservas")
    @GetMapping("/mis-reservas")
    public ResponseEntity<List<ReservaResponse>> misReservas(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(reservaService.misReservas(userDetails.getUsername()));
    }

    @Operation(summary = "Cancelar una reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        reservaService.cancelar(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}