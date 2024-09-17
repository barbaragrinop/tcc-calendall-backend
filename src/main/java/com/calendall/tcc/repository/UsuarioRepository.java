package com.calendall.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.calendall.tcc.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmail(String Email);
    List<Usuario> findAll();
}
