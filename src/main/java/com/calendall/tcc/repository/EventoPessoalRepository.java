package com.calendall.tcc.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendall.tcc.model.EventoPessoal;
import com.calendall.tcc.model.Usuario;

public interface EventoPessoalRepository extends JpaRepository<EventoPessoal, Long>{
    List<EventoPessoal> findByUsuario(Optional<Usuario> usuario);
}
