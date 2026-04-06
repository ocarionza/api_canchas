package com.canchas.api.repository;

import com.canchas.api.entity.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {

    // Filtrar por sede y/o tipo
    List<Cancha> findBySede_IdAndTipoCancha_Id(Long sedeId, Long tipoCanchaId);
    List<Cancha> findBySede_Id(Long sedeId);
    List<Cancha> findByTipoCancha_Id(Long tipoCanchaId);

    // Canchas disponibles en una fecha (excluye las que tienen todos sus horarios reservados)
    @Query("""
        SELECT c FROM Cancha c
        WHERE (:sedeId IS NULL OR c.sede.id = :sedeId)
          AND (:tipoCanchaId IS NULL OR c.tipoCancha.id = :tipoCanchaId)
    """)
    List<Cancha> findWithFilters(
            @Param("sedeId") Long sedeId,
            @Param("tipoCanchaId") Long tipoCanchaId
    );
}