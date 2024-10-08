package com.calendall.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendall.tcc.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    
}
