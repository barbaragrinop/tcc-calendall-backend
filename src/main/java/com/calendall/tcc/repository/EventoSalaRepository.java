package com.calendall.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.Sala;

public interface EventoSalaRepository extends JpaRepository<EventoSala, Long> {
    
    @Query("SELECT e FROM EventoSala e WHERE e.sala = :sala ORDER BY e.dt_evento ASC")
    List<EventoSala> findBySala(Sala sala);

    @SuppressWarnings("null")
    Optional<EventoSala> findById(Long id);

    @Query("SELECT e FROM EventoSala e WHERE e.dt_evento = CURRENT_DATE AND e.sala.id = :id_sala ORDER BY e.dt_evento ASC")
    List<EventoSala> findEventosNaDataAtualPorSala(@Param("id_sala") Long id_sala);

    @Query("SELECT e FROM EventoSala e WHERE e.dt_evento > CURRENT_DATE AND e.sala.id = :id_sala ORDER BY e.dt_evento ASC")
    List<EventoSala> findEventosAposDataAtualPorSala(@Param("id_sala") Long id_sala);

}

