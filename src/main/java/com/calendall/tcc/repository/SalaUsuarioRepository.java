package com.calendall.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendall.tcc.model.SalaUsuario;

@Repository
public interface SalaUsuarioRepository extends JpaRepository<SalaUsuario, Long> {
    
}
