package com.calendall.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calendall.tcc.model.SalaUsuario;

@Repository
public interface SalaUsuarioRepository extends JpaRepository<SalaUsuario, Long> {
    
    @Query("SELECT su FROM SalaUsuario su WHERE su.usuario.id = :idUsuario AND su.sala.id = :idSala")
    SalaUsuario findByUsuarioAndSala(Long idUsuario, Long idSala);

    @Query("SELECT su FROM SalaUsuario su WHERE su.sala.id = :idSala")
    List<SalaUsuario> findBySala(Long idSala);

    @Query("SELECT su FROM SalaUsuario su WHERE su.usuario.id = :idUsuario")
    List<SalaUsuario> findByUsuario(Long idUsuario);
}
