package com.canchas.api.repository;

import com.canchas.api.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuario_IdOrderByFechaDesc(Long usuarioId);

    boolean existsByCanchaAndHorarioAndFechaAndEstado(
            Cancha cancha, Horario horario, java.time.LocalDate fecha, Reserva.EstadoReserva estado
    );
}