package com.calendall.tcc.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.calendall.tcc.model.EventoPessoal;

public interface EventoPessoalRepository extends JpaRepository<EventoPessoal, Long>{
    
}
