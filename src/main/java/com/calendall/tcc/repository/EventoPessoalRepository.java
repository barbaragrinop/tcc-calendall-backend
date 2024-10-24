package com.calendall.tcc.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendall.tcc.model.EventoPessoal;
import com.calendall.tcc.model.Usuario;

public interface EventoPessoalRepository extends JpaRepository<EventoPessoal, Long>{
    List<EventoPessoal> findByUsuario(Optional<Usuario> usuario);

    @Query("SELECT e FROM EventoPessoal e WHERE e.evento.dt_evento = CURRENT_DATE AND e.usuario.id = :id_usuario")
    List<EventoPessoal> findEventosPessoaisNaDataAtualPorUsuario(@Param("id_usuario") Long id_usuario);

    @Query("SELECT e FROM EventoPessoal e WHERE e.evento.dt_evento > CURRENT_DATE AND e.usuario.id = :id_usuario")
    List<EventoPessoal> findEventosPessoaisAposDataAtualPorUsuario(@Param("id_usuario") Long id_usuario);
}
