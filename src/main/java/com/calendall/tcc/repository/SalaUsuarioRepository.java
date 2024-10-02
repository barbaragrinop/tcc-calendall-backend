package com.calendall.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calendall.tcc.model.SalaUsuario;

@Repository
public interface SalaUsuarioRepository extends JpaRepository<SalaUsuario, Long> {
    
    @Query("SELECT su FROM SalaUsuario su WHERE su.usuario.id = :idUsuario AND su.sala.id = :idSala")
    SalaUsuario findByUsuarioAndSala(Long idUsuario, Long idSala);
}
