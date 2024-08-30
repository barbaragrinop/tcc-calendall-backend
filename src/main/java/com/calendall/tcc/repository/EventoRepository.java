package com.calendall.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calendall.tcc.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}

