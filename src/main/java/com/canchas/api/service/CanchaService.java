package com.canchas.api.service;

import com.canchas.api.dto.CanchaResponse;
import com.canchas.api.dto.HorarioResponse;
import com.canchas.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanchaService {

    private final CanchaRepository canchaRepository;
    private final HorarioRepository horarioRepository;

    public List<CanchaResponse> listar(Long sedeId, Long tipoCanchaId) {
        return canchaRepository.findWithFilters(sedeId, tipoCanchaId)
                .stream()
                .map(c -> CanchaResponse.builder()
                        .id(c.getId())
                        .nombre(c.getNombre())
                        .descripcion(c.getDescripcion())
                        .capacidad(c.getCapacidad())
                        .imagenUrl(c.getImagenUrl())
                        .sede(c.getSede().getNombre())
                        .sedeId(c.getSede().getId())
                        .tipoCancha(c.getTipoCancha().getNombre())
                        .tipoCanchaId(c.getTipoCancha().getId())
                        .build())
                .toList();
    }

    public CanchaResponse obtener(Long id) {
        return canchaRepository.findById(id)
                .map(c -> CanchaResponse.builder()
                        .id(c.getId())
                        .nombre(c.getNombre())
                        .descripcion(c.getDescripcion())
                        .capacidad(c.getCapacidad())
                        .imagenUrl(c.getImagenUrl())
                        .sede(c.getSede().getNombre())
                        .sedeId(c.getSede().getId())
                        .tipoCancha(c.getTipoCancha().getNombre())
                        .tipoCanchaId(c.getTipoCancha().getId())
                        .build())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
    }

    public List<HorarioResponse> horariosDisponibles(Long canchaId, LocalDate fecha) {
        int diaSemana = fecha.getDayOfWeek().getValue(); // 1=Lunes … 7=Domingo
        return horarioRepository.findHorariosDisponibles(canchaId, diaSemana, fecha)
                .stream()
                .map(h -> HorarioResponse.builder()
                        .id(h.getId())
                        .diaSemana(h.getDiaSemana())
                        .horaInicio(h.getHoraInicio())
                        .horaFin(h.getHoraFin())
                        .build())
                .toList();
    }
}