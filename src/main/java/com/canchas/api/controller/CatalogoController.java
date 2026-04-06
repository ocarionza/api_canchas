package com.canchas.api.controller;

import com.canchas.api.entity.Sede;
import com.canchas.api.entity.TipoCancha;
import com.canchas.api.repository.SedeRepository;
import com.canchas.api.repository.TipoCanchaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Catálogo")
@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final SedeRepository sedeRepository;
    private final TipoCanchaRepository tipoCanchaRepository;

    @Operation(summary = "Listar todas las sedes")
    @GetMapping("/sedes")
    public ResponseEntity<List<Sede>> sedes() {
        return ResponseEntity.ok(sedeRepository.findAll());
    }

    @Operation(summary = "Listar tipos de cancha")
    @GetMapping("/tipos-cancha")
    public ResponseEntity<List<TipoCancha>> tiposCancha() {
        return ResponseEntity.ok(tipoCanchaRepository.findAll());
    }
}