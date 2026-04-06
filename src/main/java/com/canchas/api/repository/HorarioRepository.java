package com.canchas.api.repository;

import com.canchas.api.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Horarios de una cancha para un día de semana específico
    List<Horario> findByCancha_IdAndDiaSemana(Long canchaId, Integer diaSemana);

    // Horarios disponibles: los del día que NO tienen reserva activa en esa fecha
    @Query("""
        SELECT h FROM Horario h
        WHERE h.cancha.id = :canchaId
          AND h.diaSemana = :diaSemana
          AND h.id NOT IN (
              SELECT r.horario.id FROM Reserva r
              WHERE r.cancha.id = :canchaId
                AND r.fecha = :fecha
                AND r.estado = 'ACTIVA'
          )
    """)
    List<Horario> findHorariosDisponibles(
            @Param("canchaId") Long canchaId,
            @Param("diaSemana") Integer diaSemana,
            @Param("fecha") LocalDate fecha
    );
}