package com.canchas.api.service;

import com.canchas.api.dto.*;
import com.canchas.api.entity.*;
import com.canchas.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CanchaRepository canchaRepository;
    private final HorarioRepository horarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaResponse crear(ReservaRequest request, String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();
        Cancha cancha = canchaRepository.findById(request.getCanchaId())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
        Horario horario = horarioRepository.findById(request.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        boolean existe = reservaRepository.existsByCanchaAndHorarioAndFechaAndEstado(
                cancha, horario, request.getFecha(), Reserva.EstadoReserva.ACTIVA);

        if (existe) throw new RuntimeException("Ese horario ya está reservado para esa fecha");

        Reserva reserva = Reserva.builder()
                .usuario(usuario)
                .cancha(cancha)
                .horario(horario)
                .fecha(request.getFecha())
                .estado(Reserva.EstadoReserva.ACTIVA)
                .build();

        reservaRepository.save(reserva);
        return toResponse(reserva);
    }

    public List<ReservaResponse> misReservas(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();
        return reservaRepository.findByUsuario_IdOrderByFechaDesc(usuario.getId())
                .stream().map(this::toResponse).toList();
    }

    public void cancelar(Long reservaId, String correo) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (!reserva.getUsuario().getCorreo().equals(correo)) {
            throw new RuntimeException("No tienes permiso para cancelar esta reserva");
        }

        reserva.setEstado(Reserva.EstadoReserva.CANCELADA);
        reservaRepository.save(reserva);
    }

    private ReservaResponse toResponse(Reserva r) {
        return ReservaResponse.builder()
                .id(r.getId())
                .cancha(r.getCancha().getNombre())
                .sede(r.getCancha().getSede().getNombre())
                .fecha(r.getFecha())
                .horaInicio(r.getHorario().getHoraInicio())
                .horaFin(r.getHorario().getHoraFin())
                .estado(r.getEstado().name())
                .build();
    }
}