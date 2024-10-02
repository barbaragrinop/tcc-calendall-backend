package com.calendall.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calendall.tcc.model.EventoSala;
import com.calendall.tcc.model.Sala;

public interface EventoSalaRepository extends JpaRepository<EventoSala, Long> {
    
    List<EventoSala> findBySala(Sala sala);
    @SuppressWarnings("null")
    Optional<EventoSala> findById(Long id);

}

